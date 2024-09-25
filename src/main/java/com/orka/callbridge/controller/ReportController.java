package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReportController {
	
	@GetMapping("/approvemis")
	public String approvelist(Model model) {  
		return "pages/allapprovemis";
	}

	
	@GetMapping("/rejectmis")
	public String rejectlist(Model model) {  
		return "pages/allrejectmis";
	}
	
	
	@GetMapping("/holdmis")
	public String holdlist(Model model) {  
		return "pages/allholdmis";
	}
	

}
