package boets.adresbestand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AdresbestandApplication{


	public static void main(String[] args) {
		SpringApplication.run(AdresbestandApplication.class, args);
	}
}
