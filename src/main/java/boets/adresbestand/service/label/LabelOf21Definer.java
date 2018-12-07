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

        offSetMap.put(Integer.parseInt("0"), createFloats(firstValue, firstHorizontalValue));
        offSetMap.put(Integer.parseInt("1"), createFloats(secondValue, firstHorizontalValue));
        offSetMap.put(Integer.parseInt("2"), createFloats(thirdValue, firstHorizontalValue));

        offSetMap.put(Integer.parseInt("3"), createFloats(firstValue, secondHorizontalValue));
        offSetMap.put(Integer.parseInt("4"), createFloats(secondValue, secondHorizontalValue));
        offSetMap.put(Integer.parseInt("5"), createFloats(thirdValue, secondHorizontalValue));

        offSetMap.put(Integer.parseInt("6"), createFloats(firstValue, thirdHorizontalValue));
        offSetMap.put(Integer.parseInt("7"), createFloats(secondValue, thirdHorizontalValue));
        offSetMap.put(Integer.parseInt("8"), createFloats(thirdValue, thirdHorizontalValue));

        offSetMap.put(Integer.parseInt("9"), createFloats(firstValue, forthHorizontalValue));
        offSetMap.put(Integer.parseInt("10"), createFloats(secondValue, forthHorizontalValue));
        offSetMap.put(Integer.parseInt("11"), createFloats(thirdValue, forthHorizontalValue));

        offSetMap.put(Integer.parseInt("12"), createFloats(firstValue, fifthHorizontalValue));
        offSetMap.put(Integer.parseInt("13"), createFloats(secondValue, fifthHorizontalValue));
        offSetMap.put(Integer.parseInt("14"), createFloats(thirdValue, fifthHorizontalValue));

        offSetMap.put(Integer.parseInt("15"), createFloats(firstValue, sixthHorizontalValue));
        offSetMap.put(Integer.parseInt("16"), createFloats(secondValue, sixthHorizontalValue));
        offSetMap.put(Integer.parseInt("17"), createFloats(thirdValue, sixthHorizontalValue));

        offSetMap.put(Integer.parseInt("18"), createFloats(firstValue, seventhHorizontalValue));
        offSetMap.put(Integer.parseInt("19"), createFloats(secondValue, seventhHorizontalValue));
        offSetMap.put(Integer.parseInt("20"), createFloats(thirdValue, seventhHorizontalValue));

        return offSetMap;
    }

    private Float[] createFloats(String value, String value2) {
        return new Float[]{new Float(value), new Float(value2)};
    }
}
