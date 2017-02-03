package boets.adresbestand.service;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;

/**
 * Created by Asus on 3/02/2017.
 */
public class MockObject {

    public static Person createJohnDoe() {
        Address address = new Address();
        address.setStreet("Wetstraat");
        address.setHouseNumber("105");
        address.setBox("b");
        address.getMunicipality().setZipCode(1000);
        address.getMunicipality().setCity("Brussel");
        Person person =new Person();
        person.setFirstName("John");
        person.setLastName("Doh");
        person.setMainAddress(address);
        return person;
    }

    public static Person createMarieJo() {
        Address address = new Address();
        address.setStreet("Wetstraat");
        address.setHouseNumber("105");
        address.setBox("b");
        address.getMunicipality().setZipCode(1000);
        address.getMunicipality().setCity("Brussel");
        Person person =new Person();
        person.setFirstName("MarieJo");
        person.setLastName("Vervloesem");
        person.setMainAddress(address);


        return person;
    }

}
