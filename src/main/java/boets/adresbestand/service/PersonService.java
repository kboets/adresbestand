package boets.adresbestand.service;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import boets.adresbestand.repository.AddressRepository;
import boets.adresbestand.repository.PersonRepository;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.web.form.SearchObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService implements IPersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int PAGE_SIZE = 10;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Person> searchPersons(SearchObject search) {
        if (StringUtils.isNotBlank(search.getFirstName()) && StringUtils.isNotBlank(search.getLastName())) {
            return phoneNumberOnlyToHaveNumericValues(personRepository.searchPerson(StringUtils.capitalize(search.getLastName()), StringUtils.capitalize(search.getFirstName())));
        } else if (StringUtils.isBlank(search.getFirstName()) && StringUtils.isNotBlank(search.getLastName())) {
            return phoneNumberOnlyToHaveNumericValues(personRepository.findByLastNameContaining(StringUtils.capitalize(search.getLastName())));
        }
        return phoneNumberOnlyToHaveNumericValues(personRepository.findByFirstNameContaining(StringUtils.capitalize(search.getFirstName())));
    }

    @Override
    public Page<Person> findAllPersons(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "lastName");
        return personRepository.findAll(pageRequest);
    }

    @Override
    public List<Person> findAll() {
        return phoneNumberOnlyToHaveNumericValues(personRepository.findAll());
    }

    @Override
    public void savePerson(Person person) {
        person.capitalizeToUpperCase();
        Optional<Person> personOptional = personRepository.findUniquePerson(person);

        if(personOptional.isPresent()){
            savePersonWithUpdatedAddress(personOptional.get(), personOptional.map(p -> getAddressByUniqueConstraint(p.getMainAddress())).orElse(Optional.empty()));
        }  else {
            personRepository.save(person);
        }
    }

    private void savePersonWithUpdatedAddress(Person person, Optional<Address> address) {
        if(address.isPresent()) {
            person.setMainAddress(address.get());
            address.get().addPerson(person);
            personRepository.save(person);
        }
    }


    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void removePerson(Person person) {
        Address address = addressRepository.findOne(person.getMainAddress().getId());
        if (address.getPersons().size() == 1) {
            logger.info("Only one person lives in this address; remove address as wel");
            personRepository.delete(person);
            addressRepository.delete(address);
        } else {
            logger.info("More persons live in this address; do not remove address");
            personRepository.delete(person);
        }
    }


    private Optional<Address> getAddressByUniqueConstraint(Address address) {
        Address persistedAddress = null;
        if (address != null) {
            if (StringUtils.isNotEmpty(address.getBox())) {
                persistedAddress = addressRepository.findByUniqueConstraint(StringUtils.capitalize(address.getStreet()), address.getHouseNumber(), address.getBox(), address.getMunicipality().getZipCode());
            } else {
                persistedAddress = addressRepository.findByUniqueConstraint(StringUtils.capitalize(address.getStreet()), address.getHouseNumber(), address.getMunicipality().getZipCode());
            }
        }
        return Optional.ofNullable(persistedAddress);
    }

    @Override
    public List<String> savePersons(List<Person> persons) {
        List<String> savedPersons = new ArrayList<>();
        for (Person person : persons) {
            this.savePerson(person);
            logger.info("Saved Person " + person.getLastName() + " " + person.getFirstName());
            savedPersons.add("Saved Person " + person.getLastName() + " " + person.getFirstName());
            savedPersons.add(System.lineSeparator());
        }
        return savedPersons;
    }

    @Override
    public Integer getTotalSavedPersons() {
        return personRepository.findAll().size();
    }

    @Override
    public Person getPersonByUniqueId(Long id) {
        return personRepository.findOne(id);
    }

    private List<Person> phoneNumberOnlyToHaveNumericValues(List<Person> personList) {
        for(Person person:personList) {
            if(person.getPhone() != null && !StringUtils.isNumeric(person.getPhone())) {
                person.setPhone(removeAllNonNumeric(person.getPhone()));
            }
            if(person.getMobilePhone()!= null && !StringUtils.isNumeric(person.getMobilePhone())) {
                person.setMobilePhone(removeAllNonNumeric(person.getMobilePhone()));
            }
        }
        return personList;
    }

    private String removeAllNonNumeric(String value) {
        return StringUtils.deleteWhitespace(StringUtils.remove(value,"/"));
    }
}
