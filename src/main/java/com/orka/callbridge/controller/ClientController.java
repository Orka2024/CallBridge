package com.orka.callbridge.controller;

import java.util.List;
import java.util.UUID;

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

import com.orka.callbridge.entities.Client;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.forms.ClientForm;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.helper.Message;
import com.orka.callbridge.helper.MessageType;
import com.orka.callbridge.service.ClientService;
import com.orka.callbridge.service.ImageService;
import com.orka.callbridge.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/clients")
public class ClientController {

	private Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private UserService userService;

	@RequestMapping("/add")
	public String addClientView(Model model) {

		ClientForm clientForm = new ClientForm();
		// clientForm.setCName("Aniket");
		// clientForm.setCInterest(true);

		model.addAttribute("clientForm", clientForm);
		return "pages/addClients";
	}

	@RequestMapping(value = "/addsave", method = RequestMethod.POST)
	public String saveClient(@Valid @ModelAttribute ClientForm clientForm, BindingResult result,
			Authentication authentication, HttpSession session) {

		// Validate Form
		if (result.hasErrors()) {
			
			result.getAllErrors().forEach(error-> logger.info(error.toString()));
			session.setAttribute("message", Message.builder()
					.content("Please correct the following errors")
					.type(MessageType.red)
					.build());
			return "pages/addClients";
		}

		String username = Helper.getEmailOfSignedInUser(authentication);
		// form ---> client
		User userByEmail = userService.getUserByEmail(username);

		//logger.info("File information : {}",clientForm.getCImage().getOriginalFilename());
		
		String cImageFileName = UUID.randomUUID().toString();

		String fileURL = imageService.uploadImage(clientForm.getCImage(),cImageFileName);
		
		if (fileURL.startsWith("Error")) {
		    session.setAttribute("message", Message.builder()
		            .content(fileURL)
		            .type(MessageType.red)
		            .build());
		    return "pages/addClients"; // Return to the form with an error
		}
		
		Client client = new Client();

		client.setCName(clientForm.getCName());
		client.setCPhoneNo(clientForm.getCPhoneNo());
		client.setCEmail(clientForm.getCEmail());
		client.setCStatus(clientForm.getCStatus());
		client.setCLastDateContacted(clientForm.getCLastDateContacted());
		client.setCImage(fileURL);
		client.setCCloudinaryImagePublicId(cImageFileName);
		client.setCInterest(clientForm.isCInterest());
		client.setCApplyCibil(clientForm.isCApplyCibil());
		client.setUser(userByEmail);
		clientService.save(client);
		
		System.out.println(clientForm);
		// Set message to be displayed
		session.setAttribute("message", 
				Message.builder()
				.content("Contact added successfully")
				.type(MessageType.blue)
				.build());
		return "redirect:/user/clients/add";
	}

	@GetMapping("/calling")
	public String callingClients(Model model, Authentication authentication) {
		String userBySignIn= Helper.getEmailOfSignedInUser(authentication);
		User userByEmail = userService.getUserByEmail(userBySignIn);
		List<Client> clients = clientService.getByUser(userByEmail);
		
		model.addAttribute("clients", clients);
		
		return "pages/CallersCalling";
	}

}
