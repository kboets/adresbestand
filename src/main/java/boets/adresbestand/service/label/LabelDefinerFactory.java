package boets.adresbestand.service.label;

import org.springframework.stereotype.Component;

/**
 * Created by Asus on 26/12/2017.
 */
@Component
public class LabelDefinerFactory {

    public LabelDefiner retrieveLabelDefiner(int number) {
        switch (number) {
            case 21 :   return new LabelOf21Definer();
            default:    return new LabelOf21Definer();
        }

    }
}
