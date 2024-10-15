package com.orka.callbridge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.orka.callbridge.service.UserService;

import jakarta.validation.Valid;

import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.forms.Cibilclientform;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.service.CibilclientService;


@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
  
  @Autowired
	private CibilclientService cibilclientService;

	@RequestMapping(value = "/dashboard")
	public String callerDashboard() {
		return "pages/dashboard";
	}

	@RequestMapping("/profile")
	public String showUserProfile(Model model, Authentication authentication) {
		// String name = principal.getName();
		return "pages/profile";
	}

	@RequestMapping(value = "/caller/dashboard")
	public String callerCallerDashboard() {
		return "pages/dashboard";
	}

	@GetMapping("/interested")
	public String interested() {
		return "pages/interested";
	}
	
	@GetMapping("/noninterested")
	public String noninterested() {
		return "pages/noninterested";
	}
	
	@GetMapping("/dayreport")
	public String dailyreport() {
		return "pages/daycallreport";
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
	public String InterCust_table(Model model) {
		return "pages/InterestCustomer_table";
	}
	
	@GetMapping("/applycibil")
	public String CreateForm(Model model) {  
		
		Cibilclientform cibilclientform=new Cibilclientform();
		model.addAttribute("cibilclientform",cibilclientform);
		return "pages/applycibilform";

	}
	
	@RequestMapping(value="/cibilreturn",method = RequestMethod.POST)
	public String cibilreturn(@Valid  @ModelAttribute Cibilclientform cibilclientform,
            BindingResult result) { 
		
		if (result.hasErrors()) {
            // Return to form page if validation errors exist
            return "redirect:/user/applycibil";
        }

		
		System.out.println(cibilclientform);
		Cibilclient cibilclient=Cibilclient.builder()
				.clientname(cibilclientform.getClientname())
				.clientnumber(cibilclientform.getClientnumber())
				.clientemail(cibilclientform.getClientemail())
				.clientpan(cibilclientform.getClientpan())
				.clientbod(cibilclientform.getClientbod())
				.clientaddress(cibilclientform.getClientaddress())
				.clientpin(cibilclientform.getClientpin())
				.clientloanty(cibilclientform.getClientloanty())
				.clientIncome(cibilclientform.getClientIncome())				
				.build();
		Cibilclient saveclient= cibilclientService.saveCibilclient(cibilclient);
		System.out.println("New Client Save"+saveclient);
		return "redirect:/user/applycibil";	
	}
	
	
	@GetMapping("/cibilmis")
	public String Miscibil() { 		
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
