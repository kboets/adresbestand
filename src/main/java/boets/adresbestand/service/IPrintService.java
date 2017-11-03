package boets.adresbestand.service;

import boets.adresbestand.domain.Person;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.util.List;
import java.util.Optional;

/**
 * Created by Asus on 1/11/2017.
 */
public interface IPrintService {

    Optional<PDDocument> print (List<Person> persons);
}