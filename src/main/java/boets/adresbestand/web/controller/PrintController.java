package boets.adresbestand.web.controller;

import boets.adresbestand.domain.Person;
import boets.adresbestand.service.IPrintService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Asus on 4/11/2017.
 */
@Controller
public class PrintController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String FILE_PATH = "D:\\tmp\\pdf-sample.pdf";

    @Autowired
    private IPrintService printService;

    @GetMapping("/print")
    public ResponseEntity<ByteArrayResource> printAddress() throws IOException {
        List<Person> personList = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);
        byte[] data = Files.readAllBytes(path);

        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + path.getFileName().toString())
                .contentType(MediaType.APPLICATION_PDF).contentLength(data.length)
                .body(resource);

    }

//    @GetMapping("/print}")
//    public ResponseEntity<byte[]> printAddress(@PathVariable List<Person> persons) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        String filename = "output.pdf";
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        ResponseEntity<byte[]> response = null;
//
//        Optional<PDDocument> documentOptional = printService.createPdf(persons);
//        if(documentOptional.isPresent()){
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                documentOptional.get().save(byteArrayOutputStream);
//                documentOptional.get().close();
//                response = new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
//            } catch (IOException e) {
//                logger.warn(e.getCause().getMessage());
//                byte[] errorBytes = new byte[]{};
//                return new ResponseEntity<byte[]>(errorBytes,headers,HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return response;
//    }
}
