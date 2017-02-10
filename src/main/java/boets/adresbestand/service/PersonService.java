package boets.adresbestand.service;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import boets.adresbestand.repository.AddressRepository;
import boets.adresbestand.repository.PersonRepository;
import boets.adresbestand.web.form.SearchAddressForm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonService implements IPersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public void savePerson(Person person) {
        try {
            personRepository.save(person);
        } catch (DataAccessException e) {
            if (e instanceof DataIntegrityViolationException) {
                Address persistedAddress = StringUtils.isNotEmpty(person.getMainAddress().getBox()) ? addressRepository.findByUniqueConstraint(person.getMainAddress().getStreet(), person.getMainAddress().getHouseNumber(), person.getMainAddress().getBox(), person.getMainAddress().getMunicipality()) : addressRepository.findByUniqueConstraint(person.getMainAddress().getStreet(), person.getMainAddress().getHouseNumber(), person.getMainAddress().getMunicipality());
                if(persistedAddress == null){

                }
                person.setMainAddress(persistedAddress);
                personRepository.save(person);
            }
        }
    }


    public List<Person> getPersons() {
        if (persons == null) {
            persons = new ArrayList<>();
            persons.add(MockObject.createJohnDoe());
            persons.add(MockObject.createMarieJo());
        }
        return persons;
    }

}
