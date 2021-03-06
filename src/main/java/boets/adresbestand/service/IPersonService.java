package boets.adresbestand.service;


import boets.adresbestand.domain.Person;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.web.form.SearchObject;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPersonService {

    List<Person> searchPersons(SearchAddressForm searchAddressForm);

    List<Person> searchPersons(SearchObject search);

    Page<Person> findAllPersons(Integer pageNumber);

    List<Person> findAll();

    void savePerson(Person person);

    void updatePerson(Person person);

    void removePerson(Person person);

    List<String> savePersons(List<Person> persons);

    Integer getTotalSavedPersons();

    Person getPersonByUniqueId(Long id);

}
