package com.orka.callbridge.forms;


import java.lang.Override;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString 
public class ActivelistForm {
	
	private String empName;
	private String clientName;
	private String clientNumber;
	private String clientEmail;
	private String clientPan;
	private String clientBod;
	private String clientAddress;
	private String clientPin;
	private String clientLoanty;
	private String clientIncome;
	private String cibilScore;
	private String cibilStatus;
	private String cibilReason;	
	private MultipartFile cibilupload;
		
}
