package boets.adresbestand.service;

import boets.adresbestand.domain.Municipality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 3/02/2017.
 */
public interface IMunicipalityService {

    Municipality retrieveMunicipality(Integer zipCode, String city);

    /**
     * Retrieves all cities with id.
     * @return
     */
    Map<Long, String> getAllCitiesWithId();

    List<Municipality> getAllMuncipalities();
}
