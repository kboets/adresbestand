package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import boets.adresbestand.repository.PersonRepository;
import boets.adresbestand.web.form.SearchAddressForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

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
    public void createPerson(Person person) {

    }
}
