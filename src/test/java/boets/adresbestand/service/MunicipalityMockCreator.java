package boets.adresbestand.service;

import boets.adresbestand.domain.Municipality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 11/02/2017.
 */
public class MunicipalityMockCreator {

    public static Municipality createTienen(){
        Municipality tienen = new Municipality();
        tienen.setCity("Tienen");
        tienen.setZipCode(3300);
        tienen.setId(2L);
        return tienen;
    }

    public static Municipality createZichem(){
        Municipality zichem = new Municipality();
        zichem.setCity("Zichem");
        zichem.setZipCode(3271);
        zichem.setId(1L);
        return zichem;
    }

    public static Municipality createAverbode(){
        Municipality averbode = new Municipality();
        averbode.setCity("AVERBODE");
        averbode.setZipCode(3271);
        averbode.setId(3L);
        return averbode;
    }

    public static List<Municipality> create3271(){
        List<Municipality> municipalities = new ArrayList<>();
        municipalities.add(createAverbode());
        municipalities.add(createZichem());
        return municipalities;
    }
    public static List<Municipality> createRandomMunicipalities(){
        List<Municipality> municipalities = new ArrayList<>();
        municipalities.add(createAverbode());
        municipalities.add(createZichem());
        municipalities.add(createTienen());
        return municipalities;
    }


}
