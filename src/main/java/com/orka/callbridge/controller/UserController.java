package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/caller/dashboard")
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

}
