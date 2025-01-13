package com.orka.callbridge.controller;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.entities.Approvedocupload;
import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.forms.ActivelistForm;
import com.orka.callbridge.forms.Cibilclientform;
import com.orka.callbridge.forms.uploadform;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.helper.Message;
import com.orka.callbridge.helper.MessageType;
import com.orka.callbridge.service.ActivelistService;
import com.orka.callbridge.service.ApprovedocuploadService;
import com.orka.callbridge.service.CibilclientService;
import com.orka.callbridge.service.ImageService;
import com.orka.callbridge.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/operations")
public class OpController {
	
    // create one folder 
    
	
	private Logger logger = LoggerFactory.getLogger(OpController.class);
	
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
	@Autowired
	private ActivelistService activelistService;
	
	@Autowired
	private ApprovedocuploadService approvedocuploadService;
	
	
	@Autowired
	private  ImageService imageService;
	
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
	public String checkreportform (@PathVariable("clientid") String clientid,Model model,HttpSession session) 
	{	   
		Optional<Cibilclient>filteredClients = cibilclientService.getCibilclientById(clientid);
		if (filteredClients.isPresent()) {
	        Cibilclient client = filteredClients.get();

	        // Adding values to session
	        session.setAttribute("cibilId",client.getClientId());
	        session.setAttribute("empName",client.getEmpname());
	        session.setAttribute("clientName",client.getClientname());
	        session.setAttribute("clientNumber",client.getClientnumber());
	        session.setAttribute("clientEmail",client.getClientemail());
	        session.setAttribute("clientPan",client.getClientpan());
	        session.setAttribute("clientBod",client.getClientbod());
	        session.setAttribute("clientAddress",client.getClientaddress());
	        session.setAttribute("clientPin",client.getClientpin());
	        session.setAttribute("clientLoanty",client.getClientloanty());
	        session.setAttribute("clientIncome",client.getClientIncome());
	        // Adding to model (optional, for immediate rendering)
	        model.addAttribute("filtered", client);	
	        ActivelistForm cibilreportreturn=new ActivelistForm();
			model.addAttribute("cibilreport", cibilreportreturn);
	    }
		return "operations/cibilreport"; 
	}
	
	@RequestMapping(value="/cibilreportsubmit",method = RequestMethod.POST)
	public String checkreport (@Valid  Activelist activelist, @Valid Approvedocupload approvedocupload,
			@ModelAttribute ActivelistForm activelistForm ,
            BindingResult result ,HttpSession session) {  		
		//logger.info("file information : {}",activelistForm.getCibilupload().getOriginalFilename());		
		String clientid =(String) session.getAttribute("cibilId");
		String emp =(String) session.getAttribute("empName");
		
		if (result.hasErrors()) {
			
            return "redirect:/operations/checkcibil/"+ emp;
        }
		
		String filename=UUID.randomUUID().toString();	
        String FileURL=imageService.cibiluploadImage(activelistForm.getCibilupload(),filename);

        activelist.setCibilId((String) session.getAttribute("cibilId"));
        activelist.setEmployeeName((String) session.getAttribute("empName"));
        activelist.setClientName((String) session.getAttribute("clientName"));
        activelist.setClientNumber((String) session.getAttribute("clientNumber"));
        activelist.setClientEmail((String) session.getAttribute("clientEmail"));
        activelist.setClientPan((String) session.getAttribute("clientPan"));
        activelist.setClientBod((String) session.getAttribute("clientBod"));
        activelist.setClientAddress((String) session.getAttribute("clientAddress"));
        activelist.setClientPin((String) session.getAttribute("clientPin"));
        activelist.setClientLoanty((String) session.getAttribute("clientLoanty"));
        activelist.setClientIncome((String) session.getAttribute("clientIncome"));
        activelist.setCibilUpload(FileURL);
        activelist.setCloudinaryImagePublicId(filename);
            
        // Generate random clientActiveNumber
        String clientName = (String) session.getAttribute("clientName");
        int randomNumber = (int)(Math.random()*(999999 - 100000 + 1)) + 100000;
        String random = clientName + randomNumber;
        activelist.setClientActiveNumber(random);        
        String path=folderPath+random;
        System.out.println("******************path***************"+path);
        File folder = new File(path);
        if (!folder.exists()) 
        {
            boolean isCreated = folder.mkdirs(); // Creates parent directories if necessary
            if (isCreated) {
                System.out.println("Folder created successfully: " + path);
            } else {
                System.out.println("Failed to create the folder: " + path);
            }
        } 
        else
        {
            System.out.println("Folder already exists: " + path);
        }
        
        // Set applyStatus based on cibilStatus
        String cibilStatus = activelistForm.getCibilStatus();
        if ("Approve".equals(cibilStatus))
        {
            approvedocupload.setApprovecaseid(clientid);
            approvedocupload.setClientActiveNumber(random);
            approvedocupload.setClientName(clientName);
            activelist.setApplyStatus(3);   
        } 
        else if ("Reject".equals(cibilStatus)) 
        {
            activelist.setApplyStatus(4);
        } 
        else if ("Hold".equals(cibilStatus))
        {
            activelist.setApplyStatus(5);
        }
        
		Activelist active=Activelist.builder()
				.cibilScore(activelistForm.getCibilScore())
				.cibilStatus(cibilStatus)
				.cibilReason(activelistForm.getCibilReason())
				.build();    	
		Activelist savecibilrepo=activelistService.saveActivelist(activelist);	
		Approvedocupload saveapprovedocupload=approvedocuploadService.saveApprovedocuploadService(approvedocupload);
		
        return "redirect:/operations/checkcibil/"+ emp;
	}
	
	@GetMapping("/uploaddocshow")
	public String uploaddocreport (Model model) 
	{	
		List<Approvedocupload> approvedocument= approvedocuploadService.getapprovedocuploadall();
		model.addAttribute("document",approvedocument);
		return "operations/updoc"; 
	}
		
	@GetMapping("/returnshowdoc/{approvecaseid}")
	public String returnshowdoc(@PathVariable("approvecaseid") String approvecaseid, Model model,HttpSession session) {
		
		Optional<Approvedocupload> approvedocument= approvedocuploadService.getApprovedocuploadById(approvecaseid);
		if(approvedocument.isPresent())
		{
			Approvedocupload getview = approvedocument.get();
			session.setAttribute("folder", getview.getClientActiveNumber());
			model.addAttribute("viewfile",getview);	 
		}
	    return "operations/viewdownload";
	}
	
    String folderPath = "src/main/resources/uploadedclientdocument/";	

	
	@GetMapping("/view/{file}")
	public ResponseEntity<Resource> viewdoc(@PathVariable("file") String file, Model model,HttpSession session) {
		String Foldername = (String) session.getAttribute("folder");
        String filePath= folderPath +Foldername+"/"+file;  
        try {
            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
            	
                // Detect MIME type dynamically
                String mimeType = Files.probeContentType(path);
                if (mimeType == null) {
                    mimeType = "application/octet-stream"; // Default binary type
                }
            	
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file + "\"")
                        .contentType(MediaType.parseMediaType(mimeType))
                        .body(resource);
            } else {
                throw new RuntimeException("File not found: " + filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while viewing the file.", e);
        }
	}

	@GetMapping("/download/{filedoc}")
	public ResponseEntity<Resource>  download(@PathVariable("filedoc") String filedoc, Model model,HttpSession session) {
		String Foldername = (String) session.getAttribute("folder");
        String filePath= folderPath +Foldername+"/"+filedoc;  

        System.out.println("Foldername : "+Foldername +"file : "+filedoc);
        
        try {
            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filedoc + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found: " + filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while downloading the file.", e);
        }
	}

	/* End Generate cibil  */
		
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
