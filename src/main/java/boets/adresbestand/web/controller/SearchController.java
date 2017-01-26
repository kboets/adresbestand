package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.web.validation.SearchAddressFormValidation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String SEARCH = "search";

    @Autowired
    private IPersonService personService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new SearchAddressFormValidation());
    }

    @GetMapping("/")
    public String getSearchPage(Model model) {
        logger.info("GetAddress");
        model.addAttribute("searchAddressForm", new SearchAddressForm());
        return SEARCH;
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
