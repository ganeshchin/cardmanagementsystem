package com.cardmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class CardmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardmanagementsystemApplication.class, args);
	}

}
