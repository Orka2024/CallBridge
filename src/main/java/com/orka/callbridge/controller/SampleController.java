package com.orka.callbridge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class SampleController {

    private static final Logger logger = LogManager.getLogger(SampleController.class);

    @GetMapping("/log")
	public String getLogDetails() {
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.error("Error log message");
		logger.warn("Warn log message");
		logger.trace("Trace log message");

		return "Payment details fetched succesfully.";
    }
}
