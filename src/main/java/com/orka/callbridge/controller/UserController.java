package com.orka.callbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import lombok.Builder;

import com.orka.callbridge.entities.User;
import com.orka.callbridge.forms.UserForm;
import com.orka.callbridge.service.UserService;

@Controller
@Builder
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signin")
	public String signin() {
		return "pages/sign-in";
	}

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
	public String processUserRegister(@ModelAttribute UserForm userForm) {
		System.out.println("User Registered.......!");
		System.out.println(userForm);

		// UserForm --> User
		User userNew = User.builder()
				.uName(userForm.getuUserName())
				.uEmail(userForm.getuEmail())
				.uPhoneNo(userForm.getuPhoneNo())
				.uPanNumber(userForm.getuPanNumber())
				.uRole(userForm.getuRole())
				.uUserName(userForm.getuUserName())
				.uPassword(userForm.getuPassword())
				.uProfilePic("https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/default-profile-picture-grey-male-icon.png")
				.build();
		User userSaved = userService.saveUser(userNew);
		System.out.println("User saved : ");
		return "redirect:/signup";
	}

}
