package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}
	  
	
	@GetMapping("/profile")
	public String showUserProfile() {
		return "admin/profile";
	}

	
	
}
