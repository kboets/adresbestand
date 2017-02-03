package boets.adresbestand.repository;

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


@RunWith(SpringRunner.class)
@DataJpaTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/boets/adresbestand/repository/MunicipalityRepositoryTest.xml")
public class MunicipalityRepositoryTest {

    @Autowired
    private MunicpalityRepository repository;

    @Test
    public void test_findCitiesByZipCode_shouldReturn_OneCity() {
        int zipCode = 1000;
        assertThat(repository.findCitiesByZipCode(zipCode), hasSize(1));
        assertThat(repository.findCitiesByZipCode(zipCode).get(0), is(equalTo("Brussel")));
    }

    @Test
    public void test_findCitiesByZipCode_shouldReturn_TwoCities() {
        int zipCode = 3271;
        assertThat(repository.findCitiesByZipCode(zipCode), hasSize(2));
    }

    @Test
    public void test_findZipCodeByCity_shouldReturnAlwaysOne() {
        String city = "Averbode";
        int zipCode = 3271;
        assertThat(repository.findZipCodeByCity(city), is(equalTo(new Integer(zipCode))));
    }

    @Test
    public void test_findAllMunicipalities_shouldReturnThree() {
        assertThat(repository.findAll(), hasSize(3));
    }
}
