package com.orka.callbridge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.orka.callbridge.entities.User;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.service.UserService;

@ControllerAdvice
public class RootController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void addSignedInUserInformation(Model model, Authentication authentication) {

		if (authentication == null) {
			return;
		}

		System.out.println("Adding Signed In User information to the Model ");
		String username = Helper.getEmailOfSignedInUser(authentication);
		logger.info("User Logged in: {}", username);
		User userByEmail = userService.getUserByEmail(username);

		//System.out.println(userByEmail.getuEmail());
		//System.out.println(userByEmail.getuName());
		model.addAttribute("signedInUser", userByEmail);

	}
}
