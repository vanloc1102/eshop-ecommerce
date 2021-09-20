package com.eshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.eshop.common.entity", "com.eshop.admin.user"})
public class EshopBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopBackEndApplication.class, args);
	}

}
