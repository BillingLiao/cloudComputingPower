package com.ant.webPage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPageApplication.class, args);
	}

}
