package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApprovecaseController {
	
	@GetMapping("/activelist")
	public String activelistmis(Model model) {  
		return "pages/activecasemis";
	}



}
