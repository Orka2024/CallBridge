package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operations")
public class OpController {
	
	@GetMapping("/opdashboard")
	public String dashboard() {
		return "operations/dashboard";
	}
	
	@GetMapping("/profile")
	public String showUserProfile() {
		return "operations/profile";
	}

	
	@GetMapping("/opcibilList")
	public String opcibilmis() {
		return "operations/cibilmis";
	}
	
	@GetMapping("/generatecibil")
	public String generatecibil() {
		return "operations/employeecibil";
	}

	@GetMapping("/approvemis")
	public String approvelist(Model model) {  
		return "operations/opapprovemis";
	}

	
	@GetMapping("/rejectmis")
	public String rejectlist(Model model) {  
		return "operations/oprejectmis";
	}
	
	
	@GetMapping("/holdmis")
	public String holdlist(Model model) {  
		return "operations/opholdmis";
	}
	
}
