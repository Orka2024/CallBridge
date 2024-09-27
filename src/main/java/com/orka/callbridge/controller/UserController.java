package com.orka.callbridge.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orka.callbridge.helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {


	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/caller/dashboard")
	public String callerCallerDashboard() {
		return "pages/dashboard";
	}

	@RequestMapping(value = "/dashboard")
	public String callerDashboard() {
		return "pages/dashboard";
	}

	@GetMapping("/profile")
	public String showUserProfile(Authentication authentication) {
		// String name = principal.getName();
		String username = Helper.getEmailOfSignedInUser(authentication);
		logger.info("User Logged in: {}", username);
		return "pages/profile";
	}

	@GetMapping("/calling")
	public String callingClients() {
		return "pages/CallersCalling";
	}

	@RequestMapping(value = "/admin/dashboard")
	public String adminDashboard() {
		return "admin/admindashboard";
	}

	@RequestMapping(value = "/tl/dashboard")
	public String tlDashboard() {
		return "tl/tldashboard";
	}

	@RequestMapping(value = "/op/dashboard")
	public String operationsDashboard() {
		return "operations/operationsdashboard";
	}

	@RequestMapping(value = "/so/dashboard")
	public String salesofficersDashboard() {
		return "so/sodashboard";
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
