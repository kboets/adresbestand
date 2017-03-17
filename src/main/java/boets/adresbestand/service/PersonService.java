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
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
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
        if(StringUtils.isNotBlank(searchAddressForm.getFirstName()) && StringUtils.isNotBlank(searchAddressForm.getLastName())){
            return personRepository.searchPerson(StringUtils.capitalize(searchAddressForm.getLastName()), StringUtils.capitalize(StringUtils.capitalize(searchAddressForm.getFirstName())));
        } else if(StringUtils.isBlank(searchAddressForm.getFirstName()) && StringUtils.isNotBlank(searchAddressForm.getLastName())){
            return personRepository.findByLastName(StringUtils.capitalize(searchAddressForm.getLastName()));
        }
        return personRepository.findByFirstName(StringUtils.capitalize(searchAddressForm.getFirstName()));
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void savePerson(Person person)   {
        Address addressAlreadySaved = getPersistedAddress(person.getMainAddress());
        try {
            if(addressAlreadySaved != null) {
                addressAlreadySaved.addPerson(person);
                person.setMainAddress(addressAlreadySaved);
                personRepository.save(person);
            } else {
                personRepository.save(person);
            }
        } catch(DataIntegrityViolationException ex){
            logger.warn("Person could not be saved : " +person.toString());

        }

    }

    private Address getPersistedAddress(Address address) {
        Address persistedAddress = null;
        if(address !=null) {
            if(StringUtils.isNotEmpty(address.getBox())){
                persistedAddress = addressRepository.findByUniqueConstraint(address.getStreet(), address.getHouseNumber(), address.getBox(), address.getMunicipality().getZipCode());
            } else {
                persistedAddress = addressRepository.findByUniqueConstraint(address.getStreet(), address.getHouseNumber(), address.getMunicipality().getZipCode());
            }
        }
        return  persistedAddress;
    }

    @Override
    public List<String> savePersons(List<Person> persons) {
        List<String> savedPersons = new ArrayList<>();
        for(Person person : persons) {
            this.savePerson(person);
            logger.info("Saved Person " + person.getLastName() + " " +person.getFirstName());
            savedPersons.add("Saved Person " + person.getLastName() + " " +person.getFirstName());
            savedPersons.add(System.lineSeparator());
        }
        return savedPersons;
    }

    @Override
    public Integer getTotalSavedPersons() {
        return personRepository.findAll().size();
    }

}
