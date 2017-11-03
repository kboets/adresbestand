package boets.adresbestand.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

/**
 * Created by Asus on 24/10/2017.
 */
public class PdfCreator {

    public static void main(String[] args) {
        //1. create document
        PDDocument document = new PDDocument();
        PDPage blankPage = new PDPage();
        document.addPage( blankPage );
        //2. add content to document
        //https://www.tutorialspoint.com/pdfbox/pdfbox_adding_text.htm

        //3. print content

    }
}
