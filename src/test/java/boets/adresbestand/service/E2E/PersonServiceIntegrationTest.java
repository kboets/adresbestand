package boets.adresbestand.service.E2E;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.domain.Person;
import boets.adresbestand.repository.AddressRepository;
import boets.adresbestand.repository.MunicipalityRepository;
import boets.adresbestand.service.MockObject;
import boets.adresbestand.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Asus on 3/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private MunicipalityRepository repository;
    @Autowired
    private AddressRepository adresRepository;
    private Municipality brussel;

    public void setUp(){
        brussel = repository.findOne(100L);
    }
    @Test
    public void test_savePersonWithNewAddress_shouldSaveCorrect() {
        Person john = MockObject.createJohnDoe();
        assertThat(john.getId(), is(nullValue()));
        john.getMainAddress().setMunicipality(brussel);
        personService.savePerson(john);
        assertThat(john.getId(), is(notNullValue()));
    }

    @Test
    public void test_savePersonWithExistingAddress_shouldThrowError() {
        Person john = MockObject.createJohnDoe();
        john.setMainAddress(adresRepository.findOne(50L));
        personService.savePerson(john);
        assertThat(john.getId(), is(notNullValue()));
    }


}
