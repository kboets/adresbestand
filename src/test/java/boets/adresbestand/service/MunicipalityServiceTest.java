package boets.adresbestand.service;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.repository.MunicipalityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Asus on 11/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MunicipalityServiceTest {

    @Mock
    private MunicipalityRepository municipalityRepositoryMock;

    @InjectMocks
    private MunicipalityService objectUnderTest;

    @Test
    public void test_retrieveMunicipalityWithCapitalCity_shouldReturnOne(){
        int zipCode = 3300;
        String city = "TIENEN";
        //when
        when(municipalityRepositoryMock.findByZipCodeAndCity(zipCode, "Tienen")).thenReturn(MunicipalityMockCreator.createTienen());

        Municipality municipality = objectUnderTest.retrieveMunicipality(zipCode, city);

        assertThat(municipality, is(notNullValue()));

    }

    @Test
    public void test_retrieveMunicipality_shouldReturnOne(){
        //when
        when(municipalityRepositoryMock.findByZipCodeAndCity(anyInt(), anyString())).thenReturn(null);
        when(municipalityRepositoryMock.findByZipCode(3271)).thenReturn(MunicipalityMockCreator.create3271());

        Municipality municipality = objectUnderTest.retrieveMunicipality(3271, "Averbode");

        assertThat(municipality, is(notNullValue()));
        assertThat(municipality.getId(), is(equalTo(3L)));
    }
}
