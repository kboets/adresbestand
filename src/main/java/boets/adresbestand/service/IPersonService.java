package boets.adresbestand.service;


import boets.adresbestand.domain.Person;
import boets.adresbestand.web.form.SearchAddressForm;

import java.util.List;

public interface IPersonService {

    List<Person> searchPersons(SearchAddressForm searchAddressForm);

    void savePerson(Person person);
}
