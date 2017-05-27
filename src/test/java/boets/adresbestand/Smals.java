package boets.adresbestand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 20/05/2017.
 */
public class Smals {


    public void showStringUpsideDown(List<String> phrases) {
        int totalPhrases = phrases.size();
        int longest = calculateLongestString(phrases);
        for(int i=0;i<longest;i++) {
            StringBuffer buffer = new StringBuffer();
            for(String phrase : phrases){
                if(i<phrase.length()){
                    buffer.append(phrase.charAt(i));
                    buffer.append("\t");
                } else {
                    buffer.append("\t");
                }
            }
            System.out.println(buffer.toString());
        }
    }

    private int calculateLongestString(List<String> phrases) {
        int longest = 0;
        for(String phrase : phrases) {
            if(phrase.length()>longest) {
                longest=phrase.length();
            }
        }
        return longest;
    }

    public static void main(String args[]) {
        Smals smals = new Smals();
        List<String> phrases = new ArrayList<>();
        phrases.add("This is great");
        phrases.add("What a show, really amazing");
        phrases.add("Hell yeah");
        smals.showStringUpsideDown(phrases);
    }
}
