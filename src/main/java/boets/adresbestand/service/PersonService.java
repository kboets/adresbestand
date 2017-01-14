package boets.adresbestand.service;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import boets.adresbestand.repository.AddressRepository;
import boets.adresbestand.repository.PersonRepository;
import boets.adresbestand.web.form.SearchAddressForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    private List<Person> persons;

    @Override
    public List<Person> searchPersons(SearchAddressForm searchAddressForm) {
//        if(StringUtils.isNotBlank(searchAddressForm.getFirstName()) && StringUtils.isNotBlank(searchAddressForm.getLastName())){
//            return personRepository.searchPerson(StringUtils.capitalize(searchAddressForm.getLastName()), StringUtils.capitalize(StringUtils.capitalize(searchAddressForm.getFirstName())));
//        } else if(StringUtils.isBlank(searchAddressForm.getFirstName()) && StringUtils.isNotBlank(searchAddressForm.getLastName())){
//            return personRepository.findByLastName(StringUtils.capitalize(searchAddressForm.getLastName()));
//        }
//        return personRepository.findByFirstName(StringUtils.capitalize(searchAddressForm.getFirstName()));
        return getPersons();
    }

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }
    
    private Person createJohnDoe() {
        Address address = new Address();
        address.setStreet("Wetstraat");
        address.setNumber("105");
        address.setBox("b");
        address.setZipCode("1000");
        address.setCity("Brussel");
        Person person =new Person();
        person.setFirstName("John");
        person.setLastName("Doh");
        person.setMainAddress(address);
        
        
        return person;
    }

    private Person createMarieJo() {
        Address address = new Address();
        address.setStreet("Wetstraat");
        address.setNumber("105");
        address.setBox("b");
        address.setZipCode("1000");
        address.setCity("Brussel");
        Person person =new Person();
        person.setFirstName("MarieJo");
        person.setLastName("Vervloesem");
        person.setMainAddress(address);


        return person;
    }

    public List<Person> getPersons(){
        if(persons == null) {
            persons=new ArrayList<>();
        } else if(persons.isEmpty()){
            persons.add(createJohnDoe());
            persons.add(createMarieJo());
        }
        return  persons;
    }

}
