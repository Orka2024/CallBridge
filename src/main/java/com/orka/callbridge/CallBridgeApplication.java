package com.orka.callbridge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CallBridgeApplication {

	private static final Logger LOGGER = LogManager.getLogger(CallBridgeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CallBridgeApplication.class, args);
		System.out.println("Hello CallBridge in SpringBoot.......!");
		LOGGER.trace("trace");
		LOGGER.debug("debug");
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.error("error");
		LOGGER.fatal("fatal");
	}

}
