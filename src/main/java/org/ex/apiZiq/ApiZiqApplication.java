package org.ex.apiZiq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiZiqApplication {
	
	private static final Logger log = LogManager.getLogger(ApiZiqApplication.class);

	public static void main(String[] args) {
		log.info("the main() method is called...");
		SpringApplication.run(ApiZiqApplication.class, args);		
		log.info("ready to start...");
	}

}
