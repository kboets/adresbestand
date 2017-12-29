package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.label.LabelDefiner;
import boets.adresbestand.service.label.LabelDefinerFactory;
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
    @Autowired
    private LabelDefinerFactory labelDefinerFactory;

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
            LabelDefiner labelDefiner = labelDefinerFactory.retrieveLabelDefiner(TOTAL_ADDRESSES_PAGE);
            Map<Integer, Float[]> offSetMap = labelDefiner.retrieveStylingMap();
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
