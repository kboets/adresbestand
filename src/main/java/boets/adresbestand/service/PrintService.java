package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Asus on 1/11/2017.
 */
@Service
public class PrintService implements IPrintService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int TOTAL_ADDRESSES_PAGE = 21;

    private static Map<Integer, Float[]> offSetMap = new HashMap<>();

    static {
        offSetMap.put(new Integer(0), createFloats("35", "750"));
        offSetMap.put(new Integer(1), createFloats("265", "750"));
        offSetMap.put(new Integer(2), createFloats("495", "750"));

        offSetMap.put(new Integer(3), createFloats("35", "650"));
        offSetMap.put(new Integer(4), createFloats("265", "650"));
        offSetMap.put(new Integer(5), createFloats("495", "650"));

        offSetMap.put(new Integer(6), createFloats("35", "550"));
        offSetMap.put(new Integer(7), createFloats("265", "550"));
        offSetMap.put(new Integer(8), createFloats("495", "550"));

        offSetMap.put(new Integer(9), createFloats("35", "450"));
        offSetMap.put(new Integer(10), createFloats("265", "450"));
        offSetMap.put(new Integer(11), createFloats("495", "450"));

        offSetMap.put(new Integer(12), createFloats("35", "350"));
        offSetMap.put(new Integer(13), createFloats("265", "350"));
        offSetMap.put(new Integer(14), createFloats("495", "350"));

        offSetMap.put(new Integer(15), createFloats("35", "250"));
        offSetMap.put(new Integer(16), createFloats("265", "250"));
        offSetMap.put(new Integer(17), createFloats("495", "250"));

        offSetMap.put(new Integer(18), createFloats("35", "150"));
        offSetMap.put(new Integer(19), createFloats("265", "150"));
        offSetMap.put(new Integer(20), createFloats("495", "150"));

    }
    public Optional<PDDocument> print(List<Person> persons) {
        //1. create document
        PDDocument document = createPdfDocument(persons.size());
        //2. add content to document
        try {
            addContentPdf(persons,document);
            document.save(new File("C:/tmp/load.pdf"));
            document.close();
        } catch (IOException e) {
            logger.warn("Exception occured while setting content to pdf" + e);
        }
        return Optional.ofNullable(document);
    }

    private void addContentPdf(List<Person> persons, PDDocument document) throws IOException{
        for(int i=0;i<document.getNumberOfPages();i++){
            PDPage page = document.getPage(i);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            int end = (i+1)*TOTAL_ADDRESSES_PAGE;
            int start=i*TOTAL_ADDRESSES_PAGE+i;
            int realEnd;
            if(persons.size()>end) {
                realEnd = end;
            } else {
                realEnd=persons.size();
            }
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            int offSet = 0;
            for(int j=start;j<realEnd;j++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(offSetMap.get(offSet)[0],offSetMap.get(offSet)[1]);
                contentStream.setLeading(14.5f);
                //contentStream.setLeading(leadingMap.get(j));
                contentStream.showText(persons.get(0).printPersonName());
                contentStream.newLine();
                contentStream.showText(persons.get(0).printStreetAndNumber());
                contentStream.newLine();
                contentStream.showText(persons.get(0).printMunicipale());
                contentStream.endText();
                offSet++;
            }
            contentStream.close();

        }
    }

    private int numberOfPersonsToPrint(int pageSize, int listSize) {
        return pageSize*TOTAL_ADDRESSES_PAGE;
    }

    private PDDocument createPdfDocument(int size) {
        PDDocument document = new PDDocument();
        int totalPages = calculateTotalNumberOfPages(size);
        for (int i=0;i<totalPages;i++) {
            PDPage blankPage = new PDPage();
            document.addPage( blankPage );
        }

        return document;
    }

    private int calculateTotalNumberOfPages(int size) {
        if(size<=TOTAL_ADDRESSES_PAGE) {
            return 1;
        } else {
            return (size/TOTAL_ADDRESSES_PAGE)+1;
        }
    }

    private static Float[] createFloats(String value, String value2) {
        Float[] floats = new Float[]{new Float(value), new Float(value2)};
        return floats;
    }
}
