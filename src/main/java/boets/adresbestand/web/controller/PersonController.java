package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    private final static String CREATE_UPDATE_PAGE = "create_update";

    @Autowired
    private IPersonService personService;


    @GetMapping("/create")
    public String getCreatePersonPage(Model model ) {
        model.addAttribute("person" , new Person());
        return CREATE_UPDATE_PAGE;
    }

    @GetMapping("/update/{personId}")
    public String getUpdatePersonPage(@PathVariable Long personId, Model model ) {
        model.addAttribute("person", personService.getPersonByUniqueId(personId));
        return CREATE_UPDATE_PAGE;
    }


    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("person") Person person) {
       personService.savePerson(person);

       return  CREATE_UPDATE_PAGE;
    }
}
