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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<Person> searchPersons(SearchAddressForm searchAddressForm) {
        if(StringUtils.isNotBlank(searchAddressForm.getFirstName()) && StringUtils.isNotBlank(searchAddressForm.getLastName())){
            return personRepository.searchPerson(StringUtils.capitalize(searchAddressForm.getLastName()), StringUtils.capitalize(StringUtils.capitalize(searchAddressForm.getFirstName())));
        } else if(StringUtils.isBlank(searchAddressForm.getFirstName()) && StringUtils.isNotBlank(searchAddressForm.getLastName())){
            return personRepository.findByLastNameContaining(StringUtils.capitalize(searchAddressForm.getLastName()));
        }
        return personRepository.findByFirstNameContaining(StringUtils.capitalize(searchAddressForm.getFirstName()));
    }

    @Override
    public Page<Person> findAllPersons(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE, Sort.Direction.ASC, "lastName");
        return personRepository.findAll(pageRequest);
    }

    @Override
    public void savePerson(Person person)   {
        Address addressAlreadySaved = getAddressByUniqueConstraint(person.getMainAddress());
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

    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void removePerson(Person person) {
        Address address = addressRepository.findOne(person.getMainAddress().getId());
        if(address.getPersons().size() == 1) {
            logger.info("Only one person lives in this address; remove address as wel");
            personRepository.delete(person);
            addressRepository.delete(address);
        } else {
            logger.info("More persons live in this address; do not remove address");
            personRepository.delete(person);
        }
    }


    private Address getAddressByUniqueConstraint(Address address) {
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

    @Override
    public Person getPersonByUniqueId(Long id) {
        return personRepository.findOne(id);
    }

}
