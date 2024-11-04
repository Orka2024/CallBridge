package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/So")
public class SoController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "so/dashboard";
	}
	  
	
	@GetMapping("/profile")
	public String showUserProfile() {
		return "so/profile";
	}


	
}
