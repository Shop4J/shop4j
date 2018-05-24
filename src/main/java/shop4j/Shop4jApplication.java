package shop4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Shop4jApplication {
	public static void main(String[] args) {
		SpringApplication.run(Shop4jApplication.class, args);
	}
}
