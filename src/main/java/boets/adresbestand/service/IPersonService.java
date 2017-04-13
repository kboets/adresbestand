package boets.adresbestand.service;


import boets.adresbestand.domain.Person;
import boets.adresbestand.web.form.SearchAddressForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPersonService {

    List<Person> searchPersons(SearchAddressForm searchAddressForm);

    Page<Person> findAllPersons(Integer pageNumber);

    void savePerson(Person person);

    List<String> savePersons(List<Person> persons);

    Integer getTotalSavedPersons();

    Person getPersonByUniqueId(Long id);
}
