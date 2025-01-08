package com.orka.callbridge.forms;


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
public class ApprovedocuploadForm {	
	
	private MultipartFile pancard;
	private MultipartFile adhaarcard;
	private MultipartFile drivinglc;
	private MultipartFile voterid;
}
