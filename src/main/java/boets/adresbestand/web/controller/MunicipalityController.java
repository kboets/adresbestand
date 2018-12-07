package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.service.IMunicipalityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by Asus on 22/04/2017.
 */
@RestController
public class MunicipalityController {

    @Autowired
    private IMunicipalityService municipalityService;

    private ConcurrentHashMap<Long, String> cities;

    Queue<Municipality> municipalities;

    @GetMapping(value = "/getAllCities", produces = {"application/json"})
    public @ResponseBody Map<Long,String> getAllCities(HttpServletResponse response){
        if (getCities()== null) {
            cities = new ConcurrentHashMap<>();
            cities.putAll(municipalityService.getAllCitiesWithId());
        }
        return new HashMap<Long, String>(cities);
    }

    @GetMapping(value="/getCitiesWithName",  produces = {"application/json"})
    public @ResponseBody  List<Municipality> getCity(@RequestParam("term") String cityName){
        if (municipalities == null) {
            municipalities = new ConcurrentLinkedQueue<Municipality>();
            municipalities.addAll(municipalityService.getAllMuncipalities());
        }
        return  municipalities.stream().filter(municipality -> municipality.getCity().contains(StringUtils.capitalize(cityName.toLowerCase()))).collect(Collectors.toList());
    }

    public ConcurrentHashMap<Long, String> getCities() {
        return cities;
    }

    public void setCities(ConcurrentHashMap<Long, String> cities) {
        this.cities = cities;
    }

}
