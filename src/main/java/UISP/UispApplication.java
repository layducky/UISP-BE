package UISP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UispApplication {

	public static void main(String[] args) {
		SpringApplication.run(UispApplication.class, args);
	}

}
