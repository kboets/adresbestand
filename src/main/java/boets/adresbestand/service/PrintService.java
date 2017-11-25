package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import com.google.common.annotations.VisibleForTesting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by Asus on 1/11/2017.
 */
@Service
public class PrintService implements IPrintService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int TOTAL_ADDRESSES_PAGE = 21;

    @Autowired
    private IPersonService personService;
    private static String firstValue = "45";
    private static String secondValue = "245";
    private static String thirdValue = "435";
    private static String firstHorizontalValue = "715";
    private static String secondHorizontalValue = "610";
    private static String thirdHorizontalValue = "505";
    private static String forthHorizontalValue = "400";
    private static String fifthHorizontalValue = "300";
    private static String sixthHorizontalValue = "200";
    private static String seventhHorizontalValue = "100";



    private static Map<Integer, Float[]> offSetMap = new HashMap<>();

    static {
        offSetMap.put(new Integer(0), createFloats(firstValue, firstHorizontalValue));
        offSetMap.put(new Integer(1), createFloats(secondValue, firstHorizontalValue));
        offSetMap.put(new Integer(2), createFloats(thirdValue, firstHorizontalValue));

        offSetMap.put(new Integer(3), createFloats(firstValue, secondHorizontalValue));
        offSetMap.put(new Integer(4), createFloats(secondValue, secondHorizontalValue));
        offSetMap.put(new Integer(5), createFloats(thirdValue, secondHorizontalValue));

        offSetMap.put(new Integer(6), createFloats(firstValue, thirdHorizontalValue));
        offSetMap.put(new Integer(7), createFloats(secondValue, thirdHorizontalValue));
        offSetMap.put(new Integer(8), createFloats(thirdValue, thirdHorizontalValue));

        offSetMap.put(new Integer(9), createFloats(firstValue, forthHorizontalValue));
        offSetMap.put(new Integer(10), createFloats(secondValue, forthHorizontalValue));
        offSetMap.put(new Integer(11), createFloats(thirdValue, forthHorizontalValue));

        offSetMap.put(new Integer(12), createFloats(firstValue, fifthHorizontalValue));
        offSetMap.put(new Integer(13), createFloats(secondValue, fifthHorizontalValue));
        offSetMap.put(new Integer(14), createFloats(thirdValue, fifthHorizontalValue));

        offSetMap.put(new Integer(15), createFloats(firstValue, sixthHorizontalValue));
        offSetMap.put(new Integer(16), createFloats(secondValue, sixthHorizontalValue));
        offSetMap.put(new Integer(17), createFloats(thirdValue, sixthHorizontalValue));

        offSetMap.put(new Integer(18), createFloats(firstValue, seventhHorizontalValue));
        offSetMap.put(new Integer(19), createFloats(secondValue, seventhHorizontalValue));
        offSetMap.put(new Integer(20), createFloats(thirdValue, seventhHorizontalValue));

    }

    @Override
    public byte[] createPdf(Long personId) {
        List<Person> personList = new ArrayList<>();
        personList.add(personService.getPersonByUniqueId(personId));
        return transformPDDocument(createPDDocument(personList));
    }

    @Override
    public byte[] createPdf(List persons) {
        return transformPDDocument(createPDDocument(persons));
    }

    @VisibleForTesting
    private Optional<PDDocument> createPDDocument(List<Person> persons) {
        //1. create document
        PDDocument document = createPdfDocument(persons.size());
        //2. add content to document
        try {
            addContentPdf(persons, document);
        } catch (IOException e) {
            logger.warn("Exception occured while setting content to pdf" + e);
        }
        return Optional.ofNullable(document);
    }

    @VisibleForTesting
    private byte[] transformPDDocument(Optional<PDDocument> documentOptional) {
        try {
            if (documentOptional.isPresent()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                documentOptional.get().save(byteArrayOutputStream);
                documentOptional.get().close();
                return byteArrayOutputStream.toByteArray();
            } else {
                return getDefaultPdf();
            }
        } catch (IOException eox) {
            logger.warn("Could not retrieve default error.pdf " + eox);
            return new byte[0];
        }
    }

    private byte[] getDefaultPdf() throws IOException {
        Resource resource = new ClassPathResource("static/error.pdf");
        File pdfFile = resource.getFile();
        return Files.readAllBytes(pdfFile.toPath());
    }

    private void addContentPdf(List<Person> persons, PDDocument document) throws IOException {
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            PDPage page = document.getPage(i);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            int end = (i + 1) * TOTAL_ADDRESSES_PAGE;
            int start = i * TOTAL_ADDRESSES_PAGE + i;
            int realEnd;
            if (persons.size() > end) {
                realEnd = end;
            } else {
                realEnd = persons.size();
            }
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            int offSet = 0;
            int personCounter = 0;
            for (int j = start; j < realEnd; j++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(offSetMap.get(offSet)[0], offSetMap.get(offSet)[1]);
                contentStream.setLeading(14.5f);
                if(persons.get(personCounter).hasLongName()) {
                    contentStream.showText(persons.get(personCounter).printPersonFirstName());
                    contentStream.newLine();
                    contentStream.showText(persons.get(personCounter).printPersonLastName());
                    contentStream.newLine();
                } else {
                    contentStream.showText(persons.get(personCounter).printPersonNameAndFirstName());
                    contentStream.newLine();
                }

                contentStream.showText(persons.get(personCounter).printStreetAndNumber());
                contentStream.newLine();
                contentStream.showText(persons.get(personCounter).printMunicipale());
                contentStream.endText();
                offSet++;
                personCounter++;
            }
            contentStream.close();

        }
    }

    private int numberOfPersonsToPrint(int pageSize, int listSize) {
        return pageSize * TOTAL_ADDRESSES_PAGE;
    }

    private PDDocument createPdfDocument(int size) {
        PDDocument document = new PDDocument();
        int totalPages = calculateTotalNumberOfPages(size);
        for (int i = 0; i < totalPages; i++) {
            PDPage blankPage = new PDPage();
            document.addPage(blankPage);
        }

        return document;
    }

    private int calculateTotalNumberOfPages(int size) {
        if (size <= TOTAL_ADDRESSES_PAGE) {
            return 1;
        } else {
            return (size / TOTAL_ADDRESSES_PAGE) + 1;
        }
    }

    private static Float[] createFloats(String value, String value2) {
        Float[] floats = new Float[]{new Float(value), new Float(value2)};
        return floats;
    }
}
