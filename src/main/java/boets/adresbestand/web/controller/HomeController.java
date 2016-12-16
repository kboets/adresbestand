package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.web.form.SearchAddressForm;
import boets.adresbestand.repository.PersonRepository;
import boets.adresbestand.web.validation.SearchAddressFormValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    private final static String INDEX_PAGE = "index";
    @Autowired
    private PersonRepository personRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new SearchAddressFormValidation());
    }

    @GetMapping("/")
    public String getSearchPage(Model model ) {
        model.addAttribute("searchAddressForm" , new SearchAddressForm());
        return INDEX_PAGE;
    }


    @PostMapping("/searchAddress")
    public String search(Model model, @Valid @ModelAttribute("searchAddressForm") SearchAddressForm searchAddressFormn, BindingResult result) {
        if(result.hasErrors()){
            return INDEX_PAGE;
        }
        model.addAttribute("searchAddressForm" , new SearchAddressForm());
        List<Person> persons = personRepository.findByLastName(searchAddressFormn.getLastName());
        model.addAttribute("persons" , persons);
        return INDEX_PAGE;
    }



}
