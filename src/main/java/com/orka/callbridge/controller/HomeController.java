package com.orka.callbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orka.callbridge.entities.User;
import com.orka.callbridge.forms.UserForm;
import com.orka.callbridge.helper.Message;
import com.orka.callbridge.helper.MessageType;
import com.orka.callbridge.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}
	
	@GetMapping("/home")
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

	@Autowired
	private UserService userService;

	@GetMapping("/signin")
	public String signin() {
		return "pages/sign-in";
	}
	/*
	 * @GetMapping("/login") public String loginin() { return "pages/sign-in"; }
	 */

	@GetMapping("/signup")
	public String signup(Model model) {
		// Create a new UserForm object
		UserForm userForm = new UserForm();
		// Add the UserForm object to the model
		model.addAttribute("userForm", userForm);
		return "pages/sign-up";
	}

	// Process User Registration
	@RequestMapping(value = "/doUserRegister", method = RequestMethod.POST)
	public String processUserRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
			HttpSession session) {
		System.out.println("User Registered.......!");
		System.out.println(userForm);

		// Validate form Data
		if (rBindingResult.hasErrors()) {
			return "pages/sign-up";
		}

		// UserForm --> User
		User userNew = User
				.builder()
				.uName(userForm.getuName())
				.uEmail(userForm.getuEmail())
				.uPhoneNo(userForm.getuPhoneNo())
				.uPanNumber(userForm.getuPanNumber())
				.uRole(userForm.getuRole())
				.uUserName(userForm.getuUserName())
				.uPassword(userForm.getuPassword())
				.uProfilePic(
						"https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/default-profile-picture-grey-male-icon.png")
				.uEnabled(userForm.isuEnable())
				.build();
		User userSaved = userService.saveUser(userNew);
		System.out.println("User saved : ");

		// Add the Message
		Message message = Message.builder().content("User Registration Successful").type(MessageType.green).build();

		session.setAttribute("message", message);
		// redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/signup";
	}

}
