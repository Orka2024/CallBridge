package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CibilformController {
	
	
	@GetMapping("/applycibil")
	public String CreateForm(Model model) {  
		return "pages/applycibilform";
	
	}

	@GetMapping("/cibilmis")
	public String Miscibil(Model model) {  
		return "pages/applycibilmis";
	
	}
	
}
