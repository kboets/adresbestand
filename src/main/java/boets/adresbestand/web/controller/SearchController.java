package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.web.validation.SearchAddressFormValidation;
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

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping("/findAll/{pageNumber}")
    public String getAllAddresses(@PathVariable Integer pageNumber, Model model) {
        logger.info("Retrieving all addresses Address");
        Page<Person> page = personService.findAllPersons(pageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

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

    @PostMapping("/searchAddress")
    public String search(Model model, @Valid @ModelAttribute("searchAddressForm") SearchAddressForm searchAddressFormn, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List <ObjectError> validationErrors = result.getAllErrors();
            List <String> errors = new ArrayList<>();
            for(ObjectError error :validationErrors){
                if(StringUtils.contains(error.getCode(), "general")){
                    errors.add(error.getCode());
                }
            }
            model.addAttribute("errors", errors);
            return SEARCH;
        }
        List<Person> persons = personService.searchPersons(searchAddressFormn);
        model.addAttribute("searchAddressForm", new SearchAddressForm());
        model.addAttribute("persons", persons);
        return SEARCH;
    }




}
