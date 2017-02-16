package boets.adresbestand.service;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import boets.adresbestand.util.MunicipalitySQLTransformator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 27/01/2017.
 */
@Service
public class DatabaseUtilService implements IDatabaseUtilService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MunicipalitySQLTransformator municipalitySQLTransformator;
    @Autowired
    private MunicipalityService municipalityService;

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
            logger.error("Could not load csv file " + e);
            return "NOT OK";
        } catch (IOException e) {
            logger.error("Could not transform csv file " + e);
            return "NOT OK";
        }
        return "OK";
    }

    @Override
    public List<Person> migratePersons() {
        List<Person> persons = new ArrayList<>();
        Map<Long, Address> addressMap = new HashMap<>();
        try {
            File adresXml = ResourceUtils.getFile("classpath:db/files/adres.xml");
            Document adresDocument = createDocument(adresXml);
            if (adresDocument != null) {
                addressMap = mapAddresses(adresDocument);
            }
            File personXml = ResourceUtils.getFile("classpath:db/files/person.xml");
            Document personDocument = createDocument(personXml);
            if (personDocument != null) {
                persons = mapPersons(personDocument, addressMap);
            }
        } catch (FileNotFoundException e) {
            logger.error("Could not load xml " + e);
        }
        return persons;
    }
    private List<Person> mapPersons(Document document, Map<Long, Address> addressMap) {
        List<Person> persons = new ArrayList<>();
        document.getDocumentElement().normalize();
        NodeList personNodeList = document.getElementsByTagName("person");
        for (int temp = 0; temp < personNodeList.getLength(); temp++) {
            Node nNode = personNodeList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Long id = Long.parseLong(eElement.getAttribute("id"));
                Person person = new Person();
                person.setFirstName(eElement.getAttribute("firstName"));
                person.setLastName(eElement.getAttribute("name"));

            }
        }
        return persons;
    }

    private Map<Long, Address> mapAddresses(Document adresDocument) {
        Map<Long, Address> addressMap = new HashMap<>();
        adresDocument.getDocumentElement().normalize();
        NodeList adresNodeList = adresDocument.getElementsByTagName("adres");
        for (int temp = 0; temp < adresNodeList.getLength(); temp++) {
            Node nNode = adresNodeList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Long id = Long.parseLong(eElement.getAttribute("adres_id"));
                Address address = new Address();
                address.setStreet(StringUtils.capitalize(eElement.getAttribute("street")));
                address.setHouseNumber(StringUtils.capitalize(eElement.getAttribute("houseNumber")));
                address.setBox(StringUtils.capitalize(eElement.getAttribute("box")));
                int zipCode =  Integer.parseInt(eElement.getAttribute("postalCode"));
                String city = StringUtils.capitalize(eElement.getAttribute("communale"));
                address.setMunicipality(municipalityService.retrieveMunicipality(zipCode, city));
                addressMap.put(id, address);
            }
        }
        return addressMap;
    }

    private Document createDocument(File file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        } catch (SAXParseException se) {
            logger.error(se.getMessage());
        } catch (IOException ioex) {
            logger.error(ioex.getMessage());
        } catch (SAXException saxex) {
            logger.error(saxex.getMessage());
        }

        return doc;
    }


}
