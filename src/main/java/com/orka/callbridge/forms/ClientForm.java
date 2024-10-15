package com.orka.callbridge.forms;

import org.springframework.web.multipart.MultipartFile;

import java.lang.Override;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientForm {

	@NotBlank(message="Name is Required")
	public String cName;

	@Column(nullable = false)
	private String cPhoneNo;

	private String cEmail;

	private String cStatus = "Nothing";

	private String cLastDateContacted;

	private MultipartFile cImage;

	private boolean cInterest = false;

	private boolean cApplyCibil = false;

}
