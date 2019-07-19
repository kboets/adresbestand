package boets.adresbestand.web.controller;

import boets.adresbestand.AdresbestandApplication;
import boets.adresbestand.domain.Municipality;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AdresbestandApplication.class)
public class MunicipalityControllerIntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String applicationName = "adresbestand";

    private String getRootUrl() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://localhost:")
                .append(port);

        logger.info("url : " + urlBuilder.toString());

        return  urlBuilder.toString();
    }

    @Test
    public void givenExistingCityName_whenGetCityByName_shouldReturnCorrectMunicipality() {
        String cityName = "Averbode";
        Municipality municipality =restTemplate.getForObject(getRootUrl()+"/getCityByName/"+cityName, Municipality.class);
        assertNotNull(municipality);
        assertThat(municipality.getCity()).isEqualTo(cityName);
    }

}