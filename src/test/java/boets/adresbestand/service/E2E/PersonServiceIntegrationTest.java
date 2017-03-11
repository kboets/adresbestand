package boets.adresbestand.service.E2E;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.domain.Person;
import boets.adresbestand.repository.AddressRepository;
import boets.adresbestand.repository.MunicipalityRepository;
import boets.adresbestand.mock.MockObject;
import boets.adresbestand.service.MunicipalityMockCreator;
import boets.adresbestand.service.PersonService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Asus on 3/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional()
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private MunicipalityRepository repository;
    @Autowired
    private AddressRepository adresRepository;
    private Municipality brussel;




    @Test
    public void test_savePersonWithNewAddress_shouldSaveCorrect() {
        Person john = MockObject.createJohnDoe();
        assertThat(john.getId(), is(nullValue()));
        john.getMainAddress().setMunicipality(MunicipalityMockCreator.createTienen());
        personService.savePerson(john);
        assertThat(john.getId(), is(notNullValue()));
    }

    //@DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    @Test
    public void test_savePersonWithExistingAddress_shouldWorkCorrectly() {
        Person john = MockObject.createJohnDoe();
        john.getMainAddress().setMunicipality(MunicipalityMockCreator.createTienen());
        personService.savePerson(john);
        Person marieJo = MockObject.createMarieJo();
        marieJo.getMainAddress().setMunicipality(MunicipalityMockCreator.createTienen());
        personService.savePerson(marieJo);
        assertThat(marieJo.getId(), is(notNullValue()));
    }


}
