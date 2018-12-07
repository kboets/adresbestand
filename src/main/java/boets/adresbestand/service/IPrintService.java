package boets.adresbestand.service;

import java.util.List;

/**
 * Created by Asus on 1/11/2017.
 */
public interface IPrintService {

    byte[] createPdf(Long personId);

    byte[] createPdf(List persons);


}
