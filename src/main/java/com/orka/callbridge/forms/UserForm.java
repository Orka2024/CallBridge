package com.orka.callbridge.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

	@NotBlank(message = "Name field is required")
	@Size(min = 3, max = 30, message = "Name must be between 3 - 30 characters")
	private String uName;

	@Email(message = "Invalid Email Address")
	private String uEmail;

	@NotBlank(message = "Phone Number is required")
	@Size(min = 10, max = 14, message = "Enter the number in +91 XXXXXXXXXX format")
	private String uPhoneNo;

	@NotBlank(message = "Pan Number is required")
	@Size(min = 10, max = 10, message = "Enter the all 10-digits")
	private String uPanNumber;

	private String uRole;

	private String uUserName;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password lenght must be minimum 8 characters")
	private String uPassword;

	private boolean uEnable;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuPhoneNo() {
		return uPhoneNo;
	}

	public void setuPhoneNo(String uPhoneNo) {
		this.uPhoneNo = uPhoneNo;
	}

	public String getuPanNumber() {
		return uPanNumber;
	}

	public void setuPanNumber(String uPanNumber) {
		this.uPanNumber = uPanNumber;
	}

	public String getuRole() {
		return uRole;
	}

	public void setuRole(String uRole) {
		this.uRole = uRole;
	}

	public String getuUserName() {
		return uUserName;
	}

	public void setuUserName(String uUserName) {
		this.uUserName = uUserName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public boolean isuEnable() {
		return uEnable;
	}

	public void setuEnable(boolean uEnable) {
		this.uEnable = uEnable;
	}

	@Override
	public String toString() {
		return "UserForm [uName=" + uName + ", uEmail=" + uEmail + ", uPhoneNo=" + uPhoneNo + ", uPanNumber="
				+ uPanNumber + ", uRole=" + uRole + ", uUserName=" + uUserName + ", uPassword=" + uPassword
				+ ", uEnable=" + uEnable + "]";
	}

}
