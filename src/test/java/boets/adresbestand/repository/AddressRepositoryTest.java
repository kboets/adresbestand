package boets.adresbestand.repository;

import boets.adresbestand.domain.Address;
import boets.adresbestand.domain.Municipality;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * Created by Asus on 3/02/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/boets/adresbestand/repository/AddressRepositoryTest.xml")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;


    @Test
    public void test_findByUniqueConstraint_withCorrectValues_shouldReturnOneAddress(){
        Municipality municipality = municipalityRepository.findOne(1L);
        Address address = addressRepository.findByUniqueConstraint("Westelsebaan", "15", municipality);
        assertThat(address, is(notNullValue()));
    }

    @Test
    public void test_findByUniqueConstraint_withWrongValues_shouldReturnOneAddress(){
        Municipality municipality = municipalityRepository.findOne(1L);
        Address address = addressRepository.findByUniqueConstraint("Westelsebaan", "13", municipality);
        assertThat(address, is(nullValue()));
    }

    @Test
    public void test_findByUniqueConstraint_withCorrectBox_shouldReturnOneAddress(){
        Municipality municipality = municipalityRepository.findOne(1L);
        Address address = addressRepository.findByUniqueConstraint("Westelsebaan", "17", "a", municipality);
        assertThat(address, is(notNullValue()));
    }
    @Test
    public void test_saveNewAddressWithMunicipality_shouldNotRaiseException() {
        Municipality municipality = municipalityRepository.findOne(1L);
        Address address = new Address();
        address.setStreet("Dorpsstraat");
        address.setHouseNumber("15");
        address.setMunicipality(municipality);
        assertThat(address.getId(), nullValue());
        addressRepository.save(address);
        assertThat(address.getId(), notNullValue());
    }

}
