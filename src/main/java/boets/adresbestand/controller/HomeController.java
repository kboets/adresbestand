package boets.adresbestand.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.form.SearchAddressForm;
import boets.adresbestand.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Asus on 17/11/2016.
 */
@Controller
public class HomeController {

    private final static String INDEX_PAGE = "index";
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String getSearchPage(Model model ) {
        model.addAttribute("searchAddressForm" , new SearchAddressForm());
        return INDEX_PAGE;
    }


    @PostMapping("/searchAddress")
    public String search(Model model, @ModelAttribute("searchAddressForm") SearchAddressForm searchAddressFormn) {
        List<Person> persons = personRepository.findByLastName(searchAddressFormn.getLastName());
        model.addAttribute("persons" , persons);
        return INDEX_PAGE;
    }



}
