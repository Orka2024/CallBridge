package com.orka.callbridge.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.forms.Cibilclientform;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.helper.Message;
import com.orka.callbridge.helper.MessageType;
import com.orka.callbridge.service.CibilclientService;

import com.orka.callbridge.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/operations")
public class OpController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@GetMapping("/dashboard")
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
	public String showcibil(Model model,Authentication authentication) { 
		String signinuser = Helper.getEmailOfSignedInUser(authentication); 
		User user  = userService.getUserByEmail(signinuser);	 
		List<Cibilclient>client= cibilclientService.getAll();
		model.addAttribute("Cibilclient",client);
		return "operations/applycibilmis";
	}

	/* end the Cibil Application */
	
	
	/* Start Generate cibil */
	
	@GetMapping("/generatecibil")
	public String generatecibil(Model model,Authentication authentication ) {
		String signinuser = Helper.getEmailOfSignedInUser(authentication); 
		User user  = userService.getUserByEmail(signinuser);	 
		List<User>employee= userService.getAllUsers();
		model.addAttribute("emp",employee);
		return "operations/employeecibil";
	}
	
	@GetMapping("/checkcibil/{employee}")
	public String reportcibil(@PathVariable("employee") String employee,Model model) 
	{
	    model.addAttribute("emp",employee); 
		List<Cibilclient>filteredClients= cibilclientService.findByEmpname(employee);
		model.addAttribute("client",filteredClients);
		return "operations/checkcibil"; 
	}
	
	@GetMapping("/checkreport/{clientid}")
	public String checkreport(@PathVariable("clientid") String clientid,Model model) 
	{	   
		Optional<Cibilclient>filteredClients = cibilclientService.getCibilclientById(clientid);
		model.addAttribute("filtered",filteredClients.get());
		return "operations/cibilreport"; 
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
