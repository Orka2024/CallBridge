package com.orka.callbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CallBridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallBridgeApplication.class, args);
		System.out.println("Hello CallBridge in SpringBoot.......!");
	}

}
