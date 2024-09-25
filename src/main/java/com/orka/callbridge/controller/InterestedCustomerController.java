package com.orka.callbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import com.orka.callbridge.entities.InterestedCustomer;
//import com.orka.callbridge.service.InterestedCustomerService;

@Controller
public class InterestedCustomerController {


	  // @Autowired
	  //  private InterestedCustomerService customerService;
	
	@GetMapping("/InterestCust")
	public String InterCust(Model model) {  
  //      model.addAttribute("customer", new InterestedCustomer());
		return "pages/InterestCust_from";
	}
	

	@GetMapping("/InterestCust_table")
		public String InterCust_table( Model model) {
			return "pages/InterestCustomer_table";
		}
	
	
	
	
	
}
