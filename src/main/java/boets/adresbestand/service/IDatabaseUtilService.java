package boets.adresbestand.service;

import boets.adresbestand.domain.Person;

import java.io.IOException;
import java.util.List;

/**
 * Created by Asus on 27/01/2017.
 */
public interface IDatabaseUtilService {

    String createMunicipalityScript() throws IOException;

    List<Person> migratePersons();
}
