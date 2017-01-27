package boets.adresbestand.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MunicipalitiesImporter {

       private static final String separator = ";";

       public List<String> generateSQLInsertFromCVS(File csvFile) throws Exception{
           InputStream is = new FileInputStream(csvFile);
           BufferedReader br = new BufferedReader(new InputStreamReader(is));
           return br.lines().map(mapToSqlInsert).collect(Collectors.toList());
       }

       public static Function<String, String> mapToSqlInsert = (line) -> {
           String[] munis = line.split(separator);
           StringBuilder sqlInsert = new StringBuilder();
           sqlInsert.append("INSERT INTO MUNICIPALITY values ( ");
           sqlInsert.append(StringUtils.capitalize(munis[0]));
           sqlInsert.append(",");
           sqlInsert.append(StringUtils.capitalize(munis[1]));
           sqlInsert.append(");");
           return sqlInsert.toString();
       };



}
