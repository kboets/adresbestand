package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import boets.adresbestand.web.form.SearchAddressForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonController {

    private final static String CREATE_PAGE = "create";

    @Autowired
    private IPersonService personService;


    @GetMapping("/create")
    public String getCreatePersonPage(Model model ) {
        model.addAttribute("person" , new Person());
        return CREATE_PAGE;
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("person") Person person) {
       personService.createPerson(person);

       return  CREATE_PAGE;
    }
}
