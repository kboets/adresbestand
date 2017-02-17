package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Asus on 27/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DatabaseUtilServiceTest {

    @Mock
    private MunicipalityService municipalityServiceMock;

    @InjectMocks
    private DatabaseUtilService objectUnderTest;

    @Ignore
    @Test
    public void createMunicipalityScript_returnsOK(){
        assertThat(objectUnderTest.createMunicipalityScript(), is(equalTo("OK")));
    }

    @Test
    public void test_migratePersons_shouldReturnAllPersonsWithCorrectAddresses() {
        //when
        when(municipalityServiceMock.retrieveMunicipality(anyInt(), anyString())).thenReturn(MunicipalityMockCreator.createAverbode());
        List<Person> personList = objectUnderTest.migratePersons();
        assertThat(personList, hasSize(69));

    }

}
