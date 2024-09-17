package com.orka.callbridge.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
	private String uName;
	private String uEmail;
	private String uPhoneNo;
	private String uPanNumber;
	private String uRole;
	private String uUserName;
	private String uPassword;

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

	@Override
	public String toString() {
		return "UserForm [uName=" + uName + ", uEmail=" + uEmail + ", uPhoneNo=" + uPhoneNo + ", uPanNumber="
				+ uPanNumber + ", uRole=" + uRole + ", uUserName=" + uUserName + ", uPassword=" + uPassword + "]";
	}

}
