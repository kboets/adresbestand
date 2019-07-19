package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Municipality;
import boets.adresbestand.service.IMunicipalityService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping(value="/getCityByName/{name}",  produces = {"application/json"})
    public @ResponseBody  Municipality getCityByName(@PathVariable("name") String cityName){
        if (municipalities == null) {
            municipalities = new ConcurrentLinkedQueue<Municipality>();
            municipalities.addAll(municipalityService.getAllMuncipalities());
        }
        List<Municipality> municipalityList = municipalities.stream().filter(municipality -> municipality.getCity().contains(cityName)).collect(Collectors.toList());
        if(municipalityList.size() != 1) {
            logger.warn(String.format("Multiple %1s municipalities found for cityName %2s",municipalityList.size(),cityName));
        }
        return municipalityList.get(0);

    }

    public ConcurrentHashMap<Long, String> getCities() {
        return cities;
    }

    public void setCities(ConcurrentHashMap<Long, String> cities) {
        this.cities = cities;
    }

}
