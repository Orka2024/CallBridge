package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/TL")
public class TLController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "TL/dashboard";
	}
	
	@GetMapping("/profile")
	public String showUserProfile() {
		return "TL/profile";
	}

	@GetMapping("/Assignin")
	public String Assigninpage() {
		return "TL/Assign_page";
	}

}
