package boets.adresbestand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Asus on 17/11/2016.
 */
@Controller
public class SearchController {

    @RequestMapping("/search")
    public String getListUsersView() {
        ModelMap model = new ModelMap();
        //model.addAttribute("users", userService.getList());
        return "index";
    }
}
