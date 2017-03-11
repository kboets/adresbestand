package boets.adresbestand.mock;

import boets.adresbestand.domain.Address;
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
        address.setStreet("Schoolstraat");
        address.setHouseNumber("105");
        address.setBox("b");
        address.setMunicipality(MunicipalityMockCreator.createTienen());
        Person person =new Person();
        person.setFirstName("Marie-Jo");
        person.setLastName("Vervloesem");
        person.setMainAddress(address);


        return person;
    }

}
