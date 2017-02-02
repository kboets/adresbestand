package boets.adresbestand.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MunicipalitySQLTransformator {

       private static final String separator = ";";
       public static String sql="INSERT INTO MUNICIPALITY (ID,ZIPCODE, CITY) values(MUNICIPALITY_S.nextVal,";

       public List<String> generateSQLInsertFromCVS(File csvFile) throws IOException{
           InputStream is = new FileInputStream(csvFile);
           BufferedReader br = new BufferedReader(new InputStreamReader(is));
           return br.lines().map(mapToSqlInsert).filter(Objects::nonNull).collect(Collectors.toList());
       }

       public static Function<String, String> mapToSqlInsert = (line) -> {
           String[] munis = line.split(separator);
           if(munis.length == 2){
               StringBuilder sqlInsert = new StringBuilder();
               sqlInsert.append(sql);
               sqlInsert.append(munis[0]);
               sqlInsert.append(",'");
               sqlInsert.append(transformToCorrectFormat(munis[1]));
               sqlInsert.append("');");
               return sqlInsert.toString();
           }
           return null;
       };


     private static String transformToCorrectFormat(String raw) {
         //check only uppercase -> to lowercase
         if(StringUtils.isAllUpperCase(raw)){
             raw = StringUtils.capitalize(raw.toLowerCase());
         }
         //check Uppercase with "-" ->
         if(StringUtils.containsAny(raw, "-")){
             String [] cities = StringUtils.split(raw, "-");
             StringBuilder builder = new StringBuilder();
             int i=1;
             for(String city:cities){
                 if(StringUtils.containsAny(city, "'")){
                     builder.append(StringUtils.capitalize(city));
                 } else {
                     builder.append(StringUtils.capitalize(city.toLowerCase()));
                 }

                 if(i<cities.length){
                     builder.append("-");
                 }
                 i++;
             }
             raw = builder.toString();
         }
         //2. check on hyphen
         if(StringUtils.containsAny(raw, "'")){
             raw = raw.replaceAll("'", "''");
         }
         return raw;
     }
}
