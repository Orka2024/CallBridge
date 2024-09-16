package com.orka.callbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/dashboard")
	public String showDashboard() {
		return "pages/dashboard";
	}

	@GetMapping("/tables")
	public String showTables() {
		return "pages/tables";
	}

	@GetMapping("/billing")
	public String showBilling() {
		return "pages/billing";
	}

	@GetMapping("/profile")
	public String showUserProfile() {
		return "pages/profile";
	}

	@GetMapping("/signin")
	public String signin() {
		return "pages/sign-in";
	}

	@GetMapping("/signup")
	public String signup() {
		return "pages/sign-up";
	}

	@GetMapping("/error404")
	public String error404() {
		return "error404";
	}

	@GetMapping("/error500")
	public String error500() {
		return "error500";
	}

	@GetMapping("/virtual-reality")
	public String virtualreality() {
		return "pages/virtual-reality";
	}

	@GetMapping("/rtl")
	public String rtl() {
		return "pages/rtl";
	}

	@GetMapping("/notifications")
	public String notifications() {
		return "pages/notifications";
	}

	@GetMapping("/icons")
	public String icons() {
		return "pages/icons";
	}

	@GetMapping("/typography")
	public String typography() {
		return "pages/typography";
	}
	@GetMapping("/map")
	public String map() {
		return "pages/map";
	}

}
