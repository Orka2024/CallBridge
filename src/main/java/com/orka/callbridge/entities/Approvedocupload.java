package com.orka.callbridge.entities;

import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;


import java.lang.Override;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="approvedocupload")
@Table(name="approvedocupload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Approvedocupload 
{

	@Id
	private String approvecaseid;
	private String ClientActiveNumber;
	private String ClientName;
	private String pancard;
	private String adhaarcard;
	private String drivinglc;
	private String voterid;
	
	
	@Override
	public String toString() {
		return "Approvedocupload [approvecaseid=" + approvecaseid + ", ClientActiveNumber=" + ClientActiveNumber
				+ ", ClientName=" + ClientName + ", pancard=" + pancard + ", adhaarcard=" + adhaarcard + ", drivinglc="
				+ drivinglc + ", voterid=" + voterid + "]";
	}


	
	




	


	
	
	


	



	


	
	





	

}
