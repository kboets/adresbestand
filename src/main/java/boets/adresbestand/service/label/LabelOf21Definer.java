package boets.adresbestand.service.label;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 26/12/2017.
 */
public class LabelOf21Definer implements LabelDefiner {

    @Override
    public Map<Integer, Float[]> retrieveStylingMap() {
        Map<Integer, Float[]> offSetMap = new HashMap<>();
        String firstValue = "45";
        String secondValue = "245";
        String thirdValue = "435";
        String firstHorizontalValue = "715";
        String secondHorizontalValue = "610";
        String thirdHorizontalValue = "505";
        String forthHorizontalValue = "400";
        String fifthHorizontalValue = "300";
        String sixthHorizontalValue = "200";
        String seventhHorizontalValue = "100";

        offSetMap.put(new Integer(0), createFloats(firstValue, firstHorizontalValue));
        offSetMap.put(new Integer(1), createFloats(secondValue, firstHorizontalValue));
        offSetMap.put(new Integer(2), createFloats(thirdValue, firstHorizontalValue));

        offSetMap.put(new Integer(3), createFloats(firstValue, secondHorizontalValue));
        offSetMap.put(new Integer(4), createFloats(secondValue, secondHorizontalValue));
        offSetMap.put(new Integer(5), createFloats(thirdValue, secondHorizontalValue));

        offSetMap.put(new Integer(6), createFloats(firstValue, thirdHorizontalValue));
        offSetMap.put(new Integer(7), createFloats(secondValue, thirdHorizontalValue));
        offSetMap.put(new Integer(8), createFloats(thirdValue, thirdHorizontalValue));

        offSetMap.put(new Integer(9), createFloats(firstValue, forthHorizontalValue));
        offSetMap.put(new Integer(10), createFloats(secondValue, forthHorizontalValue));
        offSetMap.put(new Integer(11), createFloats(thirdValue, forthHorizontalValue));

        offSetMap.put(new Integer(12), createFloats(firstValue, fifthHorizontalValue));
        offSetMap.put(new Integer(13), createFloats(secondValue, fifthHorizontalValue));
        offSetMap.put(new Integer(14), createFloats(thirdValue, fifthHorizontalValue));

        offSetMap.put(new Integer(15), createFloats(firstValue, sixthHorizontalValue));
        offSetMap.put(new Integer(16), createFloats(secondValue, sixthHorizontalValue));
        offSetMap.put(new Integer(17), createFloats(thirdValue, sixthHorizontalValue));

        offSetMap.put(new Integer(18), createFloats(firstValue, seventhHorizontalValue));
        offSetMap.put(new Integer(19), createFloats(secondValue, seventhHorizontalValue));
        offSetMap.put(new Integer(20), createFloats(thirdValue, seventhHorizontalValue));

        return offSetMap;
    }

    private Float[] createFloats(String value, String value2) {
        Float[] floats = new Float[]{new Float(value), new Float(value2)};
        return floats;
    }
}
