package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonController {

    private final static String CREATE_UPDATE_PAGE = "create_update";
    private final static String REDIRECT_CU_PAGES = "redirect:/create_update";

    @Autowired
    private IPersonService personService;


    @GetMapping("/create")
    public String getCreatePersonPage(Model model) {
        //model.addAttribute("person", new Person());
        return CREATE_UPDATE_PAGE;
    }

    @GetMapping("/update/{personId}")
    public String getUpdatePersonPage(@PathVariable Long personId, Model model) {
        model.addAttribute("person", personService.getPersonByUniqueId(personId));
        return CREATE_UPDATE_PAGE;
    }

    @GetMapping("/view/{personId}")
    public String getReadOnlyPersonPage(@PathVariable Long personId, Model model) {
        model.addAttribute("person", personService.getPersonByUniqueId(personId));
        model.addAttribute("readOnly",true);
        return CREATE_UPDATE_PAGE;
    }

    @GetMapping("/remove/{personId}")
    public String removeUpdatePersonPage(@PathVariable Long personId, Model model) {
        model.addAttribute("person", personService.getPersonByUniqueId(personId));
        personService.removePerson(personService.getPersonByUniqueId(personId));
        model.addAttribute("person", new Person());
        model.addAttribute("success", "success_remove");
        return CREATE_UPDATE_PAGE;
    }


    @PostMapping("update/createUpdate")
    public String update(Model model, @ModelAttribute("person") Person person) {
        personService.updatePerson(person);
        model.addAttribute("person", person);
        model.addAttribute("success", "success_update");
        return CREATE_UPDATE_PAGE;
    }

    @PostMapping(value = "createUpdate", produces = "application/json")
    public boolean create(@RequestBody Person person) {
        personService.savePerson(person);
        return true;

    }

}
