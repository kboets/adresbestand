package boets.adresbestand.service;

import boets.adresbestand.domain.Municipality;

/**
 * Created by Asus on 3/02/2017.
 */
public interface IMunicipalityService {

    Municipality retrieveMunicipality(Integer zipCode, String city);
}
