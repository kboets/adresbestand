package boets.adresbestand.mock;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Municipality;
import boets.adresbestand.domain.Person;
import boets.adresbestand.service.MunicipalityMockCreator;

/**
 * Created by Asus on 3/02/2017.
 */
public class MockObject {

    public static Person createJohnDoe() {
        Address address = new Address();
        address.setStreet("Schoolstraat");
        address.setHouseNumber("105");
        address.setBox("b");
        address.setMunicipality(MunicipalityMockCreator.createTienen());
        Person person =new Person();
        person.setFirstName("John");
        person.setLastName("Doh");
        person.setMainAddress(address);
        return person;
    }

    public static Person createMarieJo() {
        Address address = new Address();
        address.setStreet("Westelsebaan");
        address.setHouseNumber("15");
        address.setMunicipality(MunicipalityMockCreator.createAverbode());
        Person person =new Person();
        person.setFirstName("Marie-Jo");
        person.setLastName("Vervloesem");
        person.setMainAddress(address);


        return person;
    }

    public static Person createLowerCaseWebbPerson() {
        Person webbLowerCase = new Person();
        webbLowerCase.setFirstName("phillip");
        webbLowerCase.setLastName("webb");
        Address address = new Address();
        address.setHouseNumber("5");
        address.setStreet("westelsebaan");
        address.setMunicipality(MunicipalityMockCreator.createAverbode());
        webbLowerCase.setMainAddress(address);
        return webbLowerCase;
    }

}
