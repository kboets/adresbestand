package boets.adresbestand.util;

import org.junit.Before;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class MunicipalitiesImporterTest {

    private MunicipalitiesImporter objectUnderTest;

    private File csvFile;

    @Before
    public void setUp() {
        objectUnderTest = new MunicipalitiesImporter();


    }

    public void generateSQLInsertFromCVS_givenCSVFile_returnsListWithAllElements() throws Exception {
        //csvFile = ResourceUtils.getFile("classpath:config/sample.txt")
    }
}
