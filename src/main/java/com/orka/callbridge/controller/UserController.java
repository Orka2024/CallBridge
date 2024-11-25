package com.orka.callbridge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.orka.callbridge.service.UserService;

import jakarta.validation.Valid;

import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.forms.Cibilclientform;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.helper.Message;
import com.orka.callbridge.helper.MessageType;
import com.orka.callbridge.service.CibilclientService;


@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;


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
	
	
/*--------------------------------- Start Apply for Cibil Application----------------------------------------*/	
	
    @Autowired
	private CibilclientService cibilclientService;
	
	@GetMapping("/applycibil")
	public String CreateForm(Model model,Authentication authentication) {
		String username = Helper.getEmailOfSignedInUser(authentication);
		User user = userService.getUserByEmail(username);
//		model.addAttribute("loggedin",user);
//		System.out.println("loggedin user name"+user.getuName());
//		System.out.println("loggedin user name"+user.getuEmail());
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
		
		Cibilclient cibilclient=Cibilclient.builder()
				.empname(cibilclientform.getEmpname())
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
			
	@RequestMapping("/cibilmis")
	public String showcibil(Model model, Authentication authentication) { 
		
		String username = Helper.getEmailOfSignedInUser(authentication);
		User user = userService.getUserByEmail(username);		
		List<Cibilclient>client= cibilclientService.getAll();
		model.addAttribute("Cibilclient",client);
		return "pages/applycibilmis";
	}
	
	
	@GetMapping("/update/{up_cibilclientId}")
	public String updatecibilclient(@PathVariable("up_cibilclientId") String up_cibilclientId,Model model)
	{
		var cibilupdate = cibilclientService.getCibilclientById(up_cibilclientId);	
		model.addAttribute("cibilupdate", cibilupdate);
		model.addAttribute("up_cibilclientId", up_cibilclientId);

		return "pages/Update_applycibil";
	}
	
	@RequestMapping(value="/updateapplycibil/{up_cibilclientId}",method = RequestMethod.POST)
	public String updateapplycibil(@PathVariable("up_cibilclientId") String up_cibilclientId, 
			@ModelAttribute Cibilclientform cibilclientform,Model model) { 
		var cibilup=new Cibilclient();
		cibilup.setClientId(up_cibilclientId);
		cibilup.setClientname(cibilclientform.getClientname());
		cibilup.setClientnumber(cibilclientform.getClientnumber());
		cibilup.setClientemail(cibilclientform.getClientemail());
		cibilup.setClientpan(cibilclientform.getClientpan());
		cibilup.setClientbod(cibilclientform.getClientbod());
		cibilup.setClientaddress(cibilclientform.getClientaddress());
		cibilup.setClientpin(cibilclientform.getClientpin());
		cibilup.setClientloanty(cibilclientform.getClientloanty());
		cibilup.setClientIncome(cibilclientform.getClientIncome());
		
		System.out.println("value of cibilup is "+cibilup);

		var upcibil=cibilclientService.updateCibilclient(cibilup);
		System.out.println("value of upcibil is "+upcibil);

		logger.info("Your CibilClient Update successfully : ",upcibil);
		model.addAttribute("message",Message.builder().content("up_cibilclientId")
				.type(MessageType.green).build());		
		return "redirect:/user/cibilmis";
		
	}
	

	@GetMapping("/delete/{cibilclientId}")
	public String deletecibilclient(@PathVariable("cibilclientId") String cibilclientId)
	{
		cibilclientService.deleteCibilclient(cibilclientId);
		//logger.info("Your CibilClient deleted successfully : ",cibilclientId);
		return "redirect:/user/cibilmis";
	}
	
	
	
/*----------------------------------------End Apply for Cibil Application------------------------------------*/	
	
	


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
