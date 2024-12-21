package com.orka.callbridge.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.orka.callbridge.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.entities.clientdata;
import com.orka.callbridge.forms.Cibilclientform;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.helper.Message;
import com.orka.callbridge.helper.MessageType;
import com.orka.callbridge.service.CibilclientService;
import com.orka.callbridge.service.DataintresService;

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

	/*--------------------------------- Start Apply for Cibil Application----------------------------------------*/

	@Autowired
	private CibilclientService cibilclientService;

	@Autowired
	private DataintresService dataintresService;

	@GetMapping("/dataintrested")
	public String Data(Model model) {
		List<clientdata> datac = dataintresService.getAll();
		model.addAttribute("dataclient", datac);
		return "pages/dataintrestedmis";
	}

	@GetMapping("/applycibil/{data}")
	public String CreateForm(@PathVariable("data") String data, Model model, Authentication authentication,
			HttpSession session) {

		Optional<clientdata> filterid = dataintresService.getclientdataById(data);

		if (filterid.isPresent()) {
			clientdata Clientdata = filterid.get();
			session.setAttribute("cibilId", Clientdata.getClientdata_id());
			session.setAttribute("clientName", Clientdata.getClient_name());
			session.setAttribute("clientContact", Clientdata.getClient_contact());
			model.addAttribute("Client", Clientdata);
			Cibilclientform cibilclientform = new Cibilclientform();
			model.addAttribute("cibilclientform", cibilclientform);
		}
		
		return "pages/applycibilform";

	}

	
	  @RequestMapping(value="/cibilreturn",method = RequestMethod.POST)
	  public String cibilreturn(@Valid Cibilclient cibilclient, @ModelAttribute Cibilclientform cibilclientform,
	  BindingResult result,HttpSession session) 
	  { 
		  
	      String data = (String) session.getAttribute("cibilId");

		  if (data == null) {
		        Message errorMessage = Message.builder()
		                                       .content("Something went wrong. Kindly check the cell.")
		                                       .type(MessageType.red)
		                                       .build();
		        session.setAttribute("message", errorMessage); 
		        return "redirect:/user/applycibil/"+data ; 

				   } 
			  
	  cibilclient.setClientId((String)session.getAttribute("cibilId"));
	  cibilclient.setClientname((String)session.getAttribute("clientName"));
	  cibilclient.setClientnumber((String)session.getAttribute("clientContact"));
	 		  
		 Cibilclient cibil=Cibilclient.builder()
				  .clientemail(cibilclientform.getClientemail())
				  .clientpan(cibilclientform.getClientpan())
				  .clientbod(cibilclientform.getClientbod())
				  .clientaddress(cibilclientform.getClientaddress())
				  .clientpin(cibilclientform.getClientpin())
				  .clientloanty(cibilclientform.getClientloanty())
				  .clientIncome(cibilclientform.getClientIncome())
				  .build();		  
		  Cibilclient saveclient = cibilclientService.saveCibilclient(cibilclient);	
		  Message message = Message.builder()
				  .content("Record Added Successfully")
				  .type(MessageType.green).build();
		  session.setAttribute("message", message);
		  
	  return "redirect:/user/applycibil/"+data ; 
	  
	  }
	 

	@RequestMapping("/cibilmis")
	public String showcibil(Model model, Authentication authentication) {

		String username = Helper.getEmailOfSignedInUser(authentication);
		User user = userService.getUserByEmail(username);
		List<Cibilclient> client = cibilclientService.getAll();
		model.addAttribute("Cibilclient", client);
		return "pages/applycibilmis";
	}

	@GetMapping("/update/{up_cibilclientId}")
	public String updatecibilclient(@PathVariable("up_cibilclientId") String up_cibilclientId, Model model,HttpSession session) {
		Optional<Cibilclient> cibilupdate = cibilclientService.getCibilclientById(up_cibilclientId);		
		if (cibilupdate.isPresent()) {
			Cibilclient upClient = cibilupdate.get();
			session.setAttribute("clientId", upClient.getClientId());
			session.setAttribute("clientName", upClient.getClientname());
			session.setAttribute("clientContact",upClient.getClientnumber());
			model.addAttribute("UPClient", upClient);
			model.addAttribute("cibilupdate", cibilupdate);
		}
		return "pages/Update_applycibil";
	}

	@RequestMapping(value = "/updateapplycibil", method = RequestMethod.POST)
	public String updateapplycibil( @ModelAttribute Cibilclientform cibilclientform, Model model,HttpSession session) {
		
		String up_cibilclientId = (String) session.getAttribute("clientId");

		  if (up_cibilclientId == null)
		  {
		        Message errorMessage = Message.builder()
		                                       .content("Something went wrong cibilclient. Kindly check the cell.")
		                                       .type(MessageType.red)
		                                       .build();
					        session.setAttribute("message", errorMessage); 
					        return "redirect:/user/update/"+ up_cibilclientId ; 

	    }
		
		
		var cibilup = new Cibilclient();
		cibilup.setClientId((String)session.getAttribute("clientId"));
		cibilup.setClientname((String)session.getAttribute("clientName"));
		cibilup.setClientnumber((String)session.getAttribute("clientContact"));
		cibilup.setClientemail(cibilclientform.getClientemail());
		cibilup.setClientpan(cibilclientform.getClientpan());
		cibilup.setClientbod(cibilclientform.getClientbod());
		cibilup.setClientaddress(cibilclientform.getClientaddress());
		cibilup.setClientpin(cibilclientform.getClientpin());
		cibilup.setClientloanty(cibilclientform.getClientloanty());
		cibilup.setClientIncome(cibilclientform.getClientIncome());

		System.out.println("value of cibilup is " + cibilup);

		var upcibil = cibilclientService.updateCibilclient(cibilup);
		System.out.println("value of upcibil is " + upcibil);

		logger.info("Your CibilClient Update successfully : ", upcibil);

		  Message message = Message.builder()
				  .content("Record of Cibil Update Successfully")
				  .type(MessageType.green).build();
		  session.setAttribute("message", message);
		
		return "redirect:/user/update/"+ up_cibilclientId;
	}

	@GetMapping("/delete/{cibilclientId}")
	public String deletecibilclient(@PathVariable("cibilclientId") String cibilclientId,HttpSession session) 	
	{
		cibilclientService.deleteCibilclient(cibilclientId);
		// logger.info("Your CibilClient deleted successfully : ",cibilclientId);
		
		  Message message = Message.builder()
				  .content("Record is Deleted Successfully")
				  .type(MessageType.green).build();
		  session.setAttribute("message", message);
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
