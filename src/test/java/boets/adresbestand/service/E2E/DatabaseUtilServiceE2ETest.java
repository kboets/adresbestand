package boets.adresbestand.service.E2E;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.DatabaseUtilService;
import boets.adresbestand.service.IDatabaseUtilService;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Created by Asus on 17/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/boets/adresbestand/util/DatabaseUtilServiceE2ETest.xml")
public class DatabaseUtilServiceE2ETest {

    @Autowired
    private IDatabaseUtilService objectUnderTest;

    @Test
    public void test_mapPersons_checkSize() {
        List<Person> result = objectUnderTest.migratePersons();
        assertThat(result, hasSize(66));
    }
}
