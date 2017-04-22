package boets.adresbestand.web.controller;

import boets.adresbestand.service.IMunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Asus on 22/04/2017.
 */
@RestController
public class MunicipalityController {

    @Autowired
    private IMunicipalityService municipalityService;

    private ConcurrentHashMap<Long, String> cities;

    @GetMapping("/getAllCities")
    public @ResponseBody Map<Long,String> getAllCities(){
        if (getCities()== null) {
            cities = new ConcurrentHashMap<>();
            cities.putAll(municipalityService.getAllCitiesWithId());
        }
        Map<Long, String> hashMap = new HashMap<Long, String>(cities);
        return hashMap;
    }

    public ConcurrentHashMap<Long, String> getCities() {
        return cities;
    }

    public void setCities(ConcurrentHashMap<Long, String> cities) {
        this.cities = cities;
    }

}
