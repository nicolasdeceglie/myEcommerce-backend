package com.nicolas.myEcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class MyEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEcommerceApplication.class, args);
	}

}
