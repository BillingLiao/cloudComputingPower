package com.ant.webPage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.ant.webPage.dao"})
public class WebPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPageApplication.class, args);
	}

}
