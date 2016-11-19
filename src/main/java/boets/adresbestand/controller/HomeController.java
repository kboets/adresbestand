package boets.adresbestand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Asus on 17/11/2016.
 */
@Controller
//@RequestMapping("/adresbestand")
public class HomeController {

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }
}
