package com.orka.callbridge.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/HR")
public class HrController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "HR/dashboard";
	}
	  
	
	@GetMapping("/profile")
	public String showUserProfile() {
		return "HR/profile";
	}

	
	
}
