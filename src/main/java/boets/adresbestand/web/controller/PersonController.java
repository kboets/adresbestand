package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.web.form.SearchAddressForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

    private final static String CREATE_PAGE = "create";

    @GetMapping("/create")
    public String getCreatePersonPage(Model model ) {
        model.addAttribute("person" , new Person());
        return CREATE_PAGE;
    }
}
