package boets.adresbestand.service;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.repository.MunicipalityRepository;
import boets.adresbestand.util.MunicipalitySQLTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Asus on 11/02/2017.
 */
@Service
public class MunicipalityService implements IMunicipalityService {

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Override
    public Municipality retrieveMunicipality(Integer zipCode, String city) {
        String correctCity = MunicipalitySQLTransformator.transformToCorrectFormat(city);
        Municipality municipality = municipalityRepository.findByZipCodeAndCity(zipCode, correctCity);
        if(municipality == null) {
            List<Municipality> municipalities = municipalityRepository.findByZipCode(zipCode);
            for (Municipality municipality1:municipalities) {
                if(municipality1.getCity().equalsIgnoreCase(city)){
                    municipality = municipality1;
                    break;
                }
            }
        }
        return municipality;
    }
}
