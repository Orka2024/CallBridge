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

	
//	@GetMapping("/viewreport")
//	public String viewreport() {
//		return "TL/viewcallerreport";
//	}
//	
//	@GetMapping("/interested")
//	public String interested() {
//		return "TL/interested";
//	}
//	
//	@GetMapping("/noninterested")
//	public String noninterested() {
//		return "TL/noninterested";
//	}
//	
//
//	@GetMapping("/approvemis")
//	public String approvelist(Model model) {  
//		return "TL/Tlapprovemis";
//	}
//
//	
//	@GetMapping("/rejectmis")
//	public String rejectlist(Model model) {  
//		return "TL/Tlrejectmis";
//	}
//	
//	
//	@GetMapping("/holdmis")
//	public String holdlist(Model model) {  
//		return "TL/Tlholdmis";
//	}

}
