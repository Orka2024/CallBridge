package com.orka.callbridge.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.orka.callbridge.entities.Approvedocupload;
import com.orka.callbridge.forms.ApprovedocuploadForm;

@Service
public interface ApprovedocuploadService {
	 
	Approvedocupload saveApprovedocuploadService(Approvedocupload approvedocupload);
	
	List<Approvedocupload> getapprovedocuploadall();
	
	Optional<Approvedocupload> getApprovedocuploadById(String id);

	public String documentuploadpath(String folderName,MultipartFile file, String fileType) throws  IOException ;

    void updateDocumentFields(String documentId, String panCardFileName, String aadhaarCardFileName);

}
