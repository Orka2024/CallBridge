package com.orka.callbridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.service.CibilclientService;

import com.orka.callbridge.service.UserService;

@Controller
@RequestMapping("/operations")
public class OpController {
	
	@GetMapping("/opdashboard")
	public String dashboard() {
		return "operations/dashboard";
	}
	  
	
	@GetMapping("/profile")
	public String showUserProfile() {
		return "operations/profile";
	}

	/* start the cibil Application */
	
	@Autowired
	private UserService userService;
	
    @Autowired
	private CibilclientService cibilclientService;
    
	@RequestMapping("/opcibilList")
	public String showcibil(Model model) { 
		
		/*
		   String signinuser = Helper.getEmailOfSignedInUser(authentication); 
		   User user  = userService.getUserByEmail(signinuser);
		 */	
		List<Cibilclient>client= cibilclientService.getAll();
		model.addAttribute("Cibilclient",client);
		return "operations/applycibilmis";
	}

	/* end the Cibil Application */
	
	
	/* Start Generate cibil */
	
	@GetMapping("/generatecibil")
	public String generatecibil(Model model ) {
		
		/*
		 * String signinuser1 = Helper.getEmailOfSignedInUser(authentication); User user
		 * = userService.getUserByEmail(signinuser1);
		 * System.out.println("this is Signin Users :"+ user);
		 */
		
		List<User> employee =userService.getAllUsers();
		System.out.println("All Good employee line get value :"+ employee);

		model.addAttribute("Employee",employee);
		
		return "operations/employeecibil";
	}
	
	

	
	/* End Generate cibil */
	
	
	
	@GetMapping("/approvemis")
	public String approvelist(Model model) {  
		return "operations/opapprovemis";
	}

	
	@GetMapping("/rejectmis")
	public String rejectlist(Model model) {  
		return "operations/oprejectmis";
	}
	
	
	@GetMapping("/holdmis")
	public String holdlist(Model model) {  
		return "operations/opholdmis";
	}
	
}
