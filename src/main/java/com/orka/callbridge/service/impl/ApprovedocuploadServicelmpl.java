package com.orka.callbridge.service.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.orka.callbridge.dao.ApprovedocuploadRepo;
import com.orka.callbridge.entities.Approvedocupload;
import com.orka.callbridge.forms.ApprovedocuploadForm;
import com.orka.callbridge.service.ApprovedocuploadService;

@Service
public class ApprovedocuploadServicelmpl implements ApprovedocuploadService {

	private static final String baseDir = "src/main/resources/uploadedclientdocument/";

	@Autowired
	private	ApprovedocuploadRepo approvedocuploadRepo;
	
	@Override
	public Approvedocupload saveApprovedocuploadService(Approvedocupload approvedocupload) {	
		
		return approvedocuploadRepo.save(approvedocupload);
	}

	@Override
	public Optional<Approvedocupload> getApprovedocuploadById(String id) {		
		return approvedocuploadRepo.findById(id);
	}

	
	@Override
	public String documentuploadpath(String folderName,MultipartFile file, String fileType) throws  IOException {

        // Validate file size
        if (file.getSize() < 3000 * 1024 * 1024) { // 3000 MB in bytes
            throw new IllegalArgumentException("File size exceeds the limit of 3000 MB");
        }
			
        // Validate file type
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileExtension.matches("(jpg|jpeg|png|pdf|dox|docx|xlsx|txt)"))
        { // Adjust allowed extensions as needed
            throw new IllegalArgumentException("Invalid file type. Allowed types are JPG, JPEG, PNG, PDF");
        }
               
        String folderPath=baseDir+folderName;
        System.out.println("folder path show you ::::::   "+folderPath);
		
        // Define folder path
        File folder = new File(folderPath);
        
        // Remove any existing file for the given fileType (e.g., pancard.*)
        File[] matchingFiles = folder.listFiles((dir, name) -> name.startsWith(fileType + "."));
        if (matchingFiles != null) {
            for (File existingFile : matchingFiles) {
                existingFile.delete();
            }
        }
        
        // Generate new file name
        String Renamefile = fileType + "." + fileExtension;
        
        System.out.println("Renamefile path show you ::::::   "+ Renamefile);


        Path filePath = Paths.get(folderPath, Renamefile);

       
        // Save the file
        Files.copy(file.getInputStream(), filePath);
        
		return Renamefile;
	        
	}

	@Override
	public void updateDocumentFields(String documentId, String panCardFileName, String aadhaarCardFileName) {
		Optional<Approvedocupload> optionalRecord = approvedocuploadRepo.findById(documentId);
		 if (optionalRecord.isPresent()) {
	            Approvedocupload record = optionalRecord.get();
	            if (panCardFileName != null) {
	                record.setPancard(panCardFileName);
	            }
	            if (aadhaarCardFileName != null) {
	                record.setAdhaarcard(aadhaarCardFileName);
	            }
	            approvedocuploadRepo.save(record);
	        } 
		    else
		    {
	            System.err.println("Record not found for clientId: " + documentId);
	        }
		
	}

	@Override
	public List<Approvedocupload> getapprovedocuploadall() {
		
		return approvedocuploadRepo.findAll();
	}

	
	



}
