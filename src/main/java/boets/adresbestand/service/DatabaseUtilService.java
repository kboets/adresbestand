package boets.adresbestand.service;

import boets.adresbestand.util.MunicipalitySQLTransformator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Asus on 27/01/2017.
 */
@Service
public class DatabaseUtilService implements IDatabaseUtilService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MunicipalitySQLTransformator municipalitySQLTransformator;


    @Override
    public String createMunicipalityScript() {
        try {
            File municipalities = ResourceUtils.getFile("classpath:db/files/zipcodes.csv");
            List<String> sqlInserts = municipalitySQLTransformator.generateSQLInsertFromCVS(municipalities);
            String name = "V3__insertMunicipality.sql";
            File file = new File("D:\\javadev\\projects\\adresbestand\\src\\main\\resources\\db\\migration", name);
            file.createNewFile();
            FileUtils.writeLines(file, sqlInserts);
        } catch (FileNotFoundException e) {
            logger.error("Could not load csv file " +e);
            return "NOT OK";
        } catch (IOException e) {
            logger.error("Could not transform csv file " +e);
            return "NOT OK";
        }
        return "OK";
    }
}
