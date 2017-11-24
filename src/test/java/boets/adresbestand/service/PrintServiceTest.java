package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import boets.adresbestand.mock.MockObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class PrintServiceTest {

    @Autowired
    private IPrintService objectUnderTest;
    private List<Person> personList;
    @Before
    public void init() {
        personList = new ArrayList<>();
    }


//    @Test
//    public void givenOneAddress_createPdf_shouldReturnDocumentWithOnePage() {
//        personList.add(MockObject.createJohnDoe());
//        Optional<PDDocument> documentOptional = objectUnderTest.createP(personList);
//        assertThat(documentOptional.isPresent()).isTrue();
//        assertThat(documentOptional.get().getNumberOfPages()).isEqualTo(1);
//    }
//
//    @Test
//    public void givenThreeAddresses_createPdf_shouldReturnDocumentWithOnePage() {
//        for(int i=0;i<3;i++) {
//            personList.add(MockObject.createJohnDoe());
//        }
//        Optional<PDDocument> documentOptional = objectUnderTest.createPdf(personList);
//        assertThat(documentOptional.isPresent()).isTrue();
//        assertThat(documentOptional.get().getNumberOfPages()).isEqualTo(1);
//    }
//
//    @Test
//    public void givenSixAddresses_createPdf_shouldReturnDocumentWithOnePage() {
//        for(int i=0;i<6;i++) {
//            personList.add(MockObject.createJohnDoe());
//        }
//        Optional<PDDocument> documentOptional = objectUnderTest.createPdf(personList);
//        assertThat(documentOptional.isPresent()).isTrue();
//        assertThat(documentOptional.get().getNumberOfPages()).isEqualTo(1);
//    }
//
//    @Test
//    public void givenFullPageAddresses_createPdf_shouldReturnDocumentWithOnePage() {
//        for(int i=0;i<21;i++) {
//            personList.add(MockObject.createJohnDoe());
//        }
//        Optional<PDDocument> documentOptional = objectUnderTest.createPdf(personList);
//        assertThat(documentOptional.isPresent()).isTrue();
//        assertThat(documentOptional.get().getNumberOfPages()).isEqualTo(1);
//    }
//
//    @Test
//    public void givenMultipleFullPageAddresses_createPdf_shouldReturnDocumentWithOnePage() {
//        for(int i=0;i<44;i++) {
//            personList.add(MockObject.createJohnDoe());
//        }
//        Optional<PDDocument> documentOptional = objectUnderTest.createPdf(personList);
//        assertThat(documentOptional.isPresent()).isTrue();
//        assertThat(documentOptional.get().getNumberOfPages()).isEqualTo(3);
//    }
}
