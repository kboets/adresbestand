package boets.adresbestand.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipalitySQLTransformatorTest {

    @Autowired
    private MunicipalitySQLTransformator objectUnderTest;

    private File csvFile;

    @Test
    public void generateSQLInsertFromCVS_givenCSVFile_returnsListWithAllElements() throws Exception {
        csvFile = ResourceUtils.getFile("classpath:boets/adresbestand/util/twoZipCodes.csv");
        assertThat(csvFile, notNullValue());
        List<String> sqls = objectUnderTest.generateSQLInsertFromCVS(csvFile);
        assertThat(sqls, hasSize(2));
        String expected = "INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(1000,'Brussel');";
        assertThat(sqls.get(0), is(equalTo(expected)));
    }

    @Test
    public void generateSQLInsertFromCVS_givenCSVFileUpperAndLowerCase_returnStringsWithFirstCharacterUpperCase() throws Exception {
        csvFile = ResourceUtils.getFile("classpath:boets/adresbestand/util/twoUpperLowerCase.csv");
        List<String> sqls = objectUnderTest.generateSQLInsertFromCVS(csvFile);
        assertThat(sqls, hasSize(2));
        String expected1 = "INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(1000,'Brussel');";
        assertThat(sqls.get(0), is(equalTo(expected1)));
        String expected2 = "INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(3001,'Heverlee');";
        assertThat(sqls.get(1), is(equalTo(expected2)));
    }

    @Test
    public void generateSQLInsertFromCVS_givenCSVFileUpperCaseSpecial_returnStringWithFirstCharacterUpperCase() throws Exception {
        csvFile = ResourceUtils.getFile("classpath:boets/adresbestand/util/upperCaseSpecial.csv");
        List<String> sqls = objectUnderTest.generateSQLInsertFromCVS(csvFile);
        assertThat(sqls, hasSize(1));
        String expected1 = "INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(1340,'Ottignies-Louvain-La-Neuve');";
        assertThat(sqls.get(0), is(equalTo(expected1)));

    }

    @Test
    public void generateSQLInsertFromCVS_givenCSVFileWithHypen_returnEscapedString() throws Exception {
        csvFile = ResourceUtils.getFile("classpath:boets/adresbestand/util/hyphen.csv");
        List<String> sqls = objectUnderTest.generateSQLInsertFromCVS(csvFile);
        assertThat(sqls, hasSize(2));
        String expected1 = "INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(3700,'''S Herenelderen');";
        assertThat(sqls.get(0), is(equalTo(expected1)));
        String expected2 = "INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(4217,'Waret-L''Evêque');";
        assertThat(sqls.get(1), is(equalTo(expected2)));
    }
    @Test
    public void generateSQLInsertFromCVS_givenCSVFileWrongSeparator_returnsEmptyList() throws Exception {
        csvFile = ResourceUtils.getFile("classpath:boets/adresbestand/util/wrongSeparator.csv");
        assertThat(csvFile, notNullValue());
        List<String> sqls = objectUnderTest.generateSQLInsertFromCVS(csvFile);
        assertThat(sqls, hasSize(0));
    }
}
