package varia;

import java.util.*;

/**
 * Created by Asus on 15/08/2017.
 */
public class SmalsString {

    public void printStringUpsideDown(List<String> values) {
        Optional<String> longestString = values.stream().max(Comparator.comparingInt(String::length));
        int maximum = longestString.get().length();
        int total = values.size();
        StringBuilder builder = null;
        for(int i=0;i<maximum;i++) {
            for(int j=0;j<total;j++) {
                builder = new StringBuilder();
                for(String value:values){
                    if(i<value.length()){
                        builder.append(value.charAt(i));
                    }
                    builder.append("\t");
                }
            }
            System.out.println(builder.toString());
        }
    }


    public static void main(String [] args) {
        SmalsString smalsString = new SmalsString();
        List<String> strings = new ArrayList<>();
        strings.add("This is a test !");
        strings.add("Will it work");
        strings.add("Hell yeah");
        strings.add("Hell yeah again");
        smalsString.printStringUpsideDown(strings);

    }
}
