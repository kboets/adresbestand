package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.web.form.SearchObject;
import boets.adresbestand.web.validation.SearchAddressFormValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String SEARCH = "search";
    private final static String SEARCH_RESULT = "searchResult";

    @Autowired
    private IPersonService personService;

    @InitBinder("searchAddressForm")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new SearchAddressFormValidation());
    }

    @GetMapping("/")
    public String getSearchPage(Model model) {
        logger.info("GetAddress");
        model.addAttribute("searchAddressForm", new SearchAddressForm());
        return SEARCH;
    }
    @GetMapping("/getAllPersons")
    public String getAllPersons(Model model) {
        return SEARCH_RESULT;
    }

    @GetMapping(value = "/findAll", produces = {"application/json"})
    @ResponseBody
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @PostMapping("/resetSearch")
    public String resetSearchPage(Model model) {
        model.addAttribute("searchAddressForm", new SearchAddressForm());
        return SEARCH;
    }

    @PostMapping(value = "/searchAddress", produces = {"application/json"})
    @ResponseBody
    public List<Person> search(HttpServletRequest request, @RequestBody SearchObject searchObject) {
        List<Person> persons = personService.searchPersons(searchObject);
        return persons;
    }

}
