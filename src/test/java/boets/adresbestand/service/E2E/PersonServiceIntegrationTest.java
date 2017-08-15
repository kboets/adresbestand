package boets.adresbestand.service.E2E;

import boets.adresbestand.domain.Person;
import boets.adresbestand.mock.MockObject;
import boets.adresbestand.repository.AddressRepository;
import boets.adresbestand.service.MunicipalityMockCreator;
import boets.adresbestand.service.PersonService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private PersonService objectUnderTest;
    @Autowired
    private AddressRepository addressRepository;


    @Test
    public void test_savePersonWithNewAddress_shouldSaveCorrect() {
        Person john = MockObject.createJohnDoe();
        assertThat(john.getId(), is(nullValue()));
        john.getMainAddress().setMunicipality(MunicipalityMockCreator.createTienen());
        objectUnderTest.savePerson(john);
        assertThat(john.getId(), is(notNullValue()));
    }

    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    @Test
    public void test_savePersonWithExistingAddress_shouldWorkCorrectly() {
        Person marieJo = MockObject.createMarieJo();
        marieJo.getMainAddress().setMunicipality(MunicipalityMockCreator.createAverbode());
        objectUnderTest.savePerson(marieJo);
        assertThat(marieJo.getId(), is(notNullValue()));
    }

    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    @Test
    public void givenOnePersonOnAddress_whenRemovePerson_shouldAlsoRemoveAddress() {
        Person Webb = objectUnderTest.getPersonByUniqueId(0L);
        assertThat(Webb.getId(), is(equalTo(0L)));
        Long addressId = Webb.getMainAddress().getId();
        assertThat(Webb.getMainAddress().getPersons().size(), is(equalTo(1)));
        objectUnderTest.removePerson(Webb);
        assertThat(objectUnderTest.getPersonByUniqueId(0L), nullValue());
        assertThat(addressRepository.findOne(addressId), nullValue());
    }

    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest2.xml")
    @Test
    public void givenTwoPersonsOnAddress_whenRemovePerson_shouldNotRemoveAddress() {
        Person Webb = objectUnderTest.getPersonByUniqueId(0L);
        assertThat(Webb.getId(), is(equalTo(0L)));
        Long addressId = Webb.getMainAddress().getId();
        assertThat(Webb.getMainAddress().getPersons().size(), is(equalTo(2)));
        objectUnderTest.removePerson(Webb);
        assertThat(objectUnderTest.getPersonByUniqueId(0L), nullValue());
        assertThat(addressRepository.findOne(addressId), notNullValue());
    }


}
