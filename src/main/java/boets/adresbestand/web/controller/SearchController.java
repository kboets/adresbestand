package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.web.form.SearchObject;
import boets.adresbestand.web.validation.SearchAddressFormValidation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SearchController {

    public static final String PERSONS = "personList";
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

    @GetMapping("/findAll/{pageNumber}")
    public String getAllAddresses(HttpServletRequest request, @PathVariable Integer pageNumber, Model model) {
        logger.info("Retrieving all addresses Address");
        Page<Person> page = personService.findAllPersons(pageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        request.getSession().setAttribute(SearchController.PERSONS,page.getContent());
        model.addAttribute("pagePersons", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return SEARCH_RESULT;
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
