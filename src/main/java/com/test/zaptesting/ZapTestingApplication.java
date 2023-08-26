package com.test.zaptesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ZapTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZapTestingApplication.class, args);
	}

}
