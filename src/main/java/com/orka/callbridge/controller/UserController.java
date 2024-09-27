package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/dashboard")
	public String callerDashboard() {
		return "pages/dashboard";
	}

	@RequestMapping(value = "/admin/dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}

	@RequestMapping(value = "/guest/dashboard")
	public String guestDashboard() {
		return "guest/dashboard";
	}

	@RequestMapping(value = "/op/dashboard")
	public String operationsDashboard() {
		return "operations/dashboard";
	}

	@RequestMapping(value = "/so/dashboard")
	public String salesofficersDashboard() {
		return "so/dashboard";
	}

	@GetMapping("/profile")
	public String showUserProfile() {
		return "profile";
	}

	@GetMapping("/calling")
	public String callingClients() {
		return "pages/CallersCalling";
	}
	
	@GetMapping("/InterestCust_table")
	public String InterCust_table( Model model) {
		return "pages/InterestCustomer_table";
	}


	@GetMapping("/applycibil")
	public String CreateForm(Model model) {  
		return "pages/applycibilform";
	
	}

	@GetMapping("/cibilmis")
	public String Miscibil(Model model) {  
		return "pages/applycibilmis";
	
	}
	
	@GetMapping("/activelist")
	public String activelistmis(Model model) {  
		return "pages/activecasemis";
	}

	
	
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
