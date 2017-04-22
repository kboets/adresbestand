package boets.adresbestand.service;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.repository.MunicipalityRepository;
import boets.adresbestand.util.MunicipalitySQLTransformator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Asus on 11/02/2017.
 */
@Service
public class MunicipalityService implements IMunicipalityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Override
    public Municipality retrieveMunicipality(Integer zipCode, String city) {
        String correctCity = MunicipalitySQLTransformator.transformToCorrectFormat(city);
        Municipality municipality = municipalityRepository.findByZipCodeAndCity(zipCode, correctCity);
        if (municipality == null) {
            List<Municipality> municipalities = municipalityRepository.findByZipCode(zipCode);
            for (Municipality municipality1 : municipalities) {
                if (municipality1.getCity().equalsIgnoreCase(city)) {
                    municipality = municipality1;
                    break;
                }
            }
        }
        if (municipality == null) {
            logger.warn("Could not retrieve a Municipality with zipCode " + zipCode + " and city " + city);
        }
        return municipality;
    }

    @Override
    public Map<Long, String> getAllCitiesWithId() {
        return  municipalityRepository.findAll().stream().collect(Collectors.toMap(Municipality::getId, Municipality::getCity));
    }

}
