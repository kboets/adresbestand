package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPrintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Asus on 4/11/2017.
 */
@Controller
public class PrintController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPrintService printService;

    @GetMapping("/print/{personId}")
    public ResponseEntity<byte[]> printAddress(@PathVariable Long personId) throws IOException {
        byte[] data = printService.createPdf(personId);
        return createResponseEntity(data);

    }

    @RequestMapping(value = "/print", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<byte[]> printAddress(HttpServletRequest request) throws IOException {
        List<Person> personList = (List<Person>) request.getSession().getAttribute(SearchController.PERSONS);
        byte[] data = printService.createPdf(personList);
        return createResponseEntity(data);
    }

    private ResponseEntity<byte[]> createResponseEntity(byte[] data) {
        HttpHeaders headers = new HttpHeaders();
        String fileName = "adresbestand.pdf";
        headers.add("content-disposition", "inline;filename=" + fileName);
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
                data, headers, HttpStatus.OK);
        return response;
    }

}
