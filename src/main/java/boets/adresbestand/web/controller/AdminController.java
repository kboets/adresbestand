package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.DatabaseUtilService;
import boets.adresbestand.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 17/02/2017.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPersonService personService;

    @Autowired
    private DatabaseUtilService databaseUtilService;


    @GetMapping("/migratePersons")
    public String migratePersons(Model model ) {
        List<String> overView = new ArrayList<>();
        if(personService.getTotalSavedPersons()<10){
            List<Person> personToBeMigrated = databaseUtilService.migratePersons();
            overView.addAll(personService.savePersons(personToBeMigrated));
        } else {
            overView.add("Migration already done !");
        }
        model.addAttribute("overView", overView);
        return "migratePersons";
    }

//    @ExceptionHandler({SQLException.class,DataAccessException.class})
//    public void handleDataBaseException(DataAccessException dataEx) {
//        logger.info("A DB exception occurred");
//    }
}
