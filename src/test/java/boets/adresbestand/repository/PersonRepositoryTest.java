package boets.adresbestand.repository;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Person;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



@RunWith(SpringRunner.class)
@DataJpaTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findByPersistedLastName_shouldReturn_OneResult() {
        assertThat(personRepository.findByLastName("Webb"), hasSize(1));
    }

    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findByPersistedFirstName_shouldReturn_OneResult() {
        assertThat(personRepository.findByFirstName("Phillip"), hasSize(1));
    }

    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findByPersistedFirstNameAndLastName_shouldReturn_OneResult() {
        assertThat(personRepository.searchPerson("Webb", "Phillip"), hasSize(1));
    }

    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findByNotPersistedLastName_shouldReturn_NoResult() {
        assertThat(personRepository.findByLastName("Dua"), hasSize(0));
    }
    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findAll_shouldReturn_twoResults() {
        assertThat(personRepository.findAll(), hasSize(2));
    }

    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findMainAddressForPersistedName_shouldReturn_OneAddress() {
        assertThat(personRepository.findMainAddressForName("Webb"), hasSize(1));
        assertThat(personRepository.findMainAddressForName("Webb").get(0).getCity(), equalTo("Averbode"));
    }
    @Test
    @DatabaseSetup(value = "/boets/adresbestand/repository/PersonRepositoryTest.xml")
    public void test_findMainAddressForNotPersistedName_shouldReturn_NoAddress() {
        assertThat(personRepository.findMainAddressForName("Dua"), hasSize(0));
    }
//    @Test
//    public void test_SaveNewPersonWithNewAddress() {
//        Address address=new Address();
//        address.setStreet("MainStreet");
//        address.setNumber("5");
//        address.setCity("New-York");
//        address.setZipCode("35125");
//        Person person = new Person();
//        person.setFirstName("John");
//        person.setLastName("Doe");
//        person.setMainAddress(address);
//
//        personRepository.save(person);
//        assertThat(person.getId(), notNullValue());
//       // assertThat(person.getMainAddress().getId(), notNullValue());
//    }

}

