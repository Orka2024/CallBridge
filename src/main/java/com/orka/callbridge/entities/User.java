package com.orka.callbridge.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

	@Id
	@Column(name = "u_id")
	private String uId;

	@Column(unique = true, nullable = false, length = 10)
	private String uPanNumber;

	@Column(nullable = false, length = 30)
	private String uName;

	@Column(unique = true, length = 15)
	private String uUserName;

	@Column(unique = true, length = 50)
	private String uEmail;

	@Column(nullable = false, length = 14)
	private String uPhoneNo;

	@Column(nullable = false)
	@Getter(value = AccessLevel.NONE)
	private String uPassword;

	@Column(nullable = false)
	private String uRole;

	@Column(length = 1000)
	private String uAbout;

	private String uProfilePic;

	@Getter(value = AccessLevel.NONE)
	private boolean uEnabled = true;

	private boolean uEmailVerified = false;

	private boolean uPhoneVerified = false;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Client> clients = new ArrayList<>();

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuPanNumber() {
		return uPanNumber;
	}

	public void setuPanNumber(String uPanNumber) {
		this.uPanNumber = uPanNumber;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuUserName() {
		return uUserName;
	}

	public void setuUserName(String uUserName) {
		this.uUserName = uUserName;
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

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuRole() {
		return uRole;
	}

	public void setuRole(String uRole) {
		this.uRole = uRole;
	}

	public String getuAbout() {
		return uAbout;
	}

	public void setuAbout(String uAbout) {
		this.uAbout = uAbout;
	}

	public String getuProfilePic() {
		return uProfilePic;
	}

	public void setuProfilePic(String uProfilePic) {
		this.uProfilePic = uProfilePic;
	}

	public boolean isuEnabled() {
		return uEnabled;
	}

	public void setuEnabled(boolean uEnabled) {
		this.uEnabled = uEnabled;
	}

	public boolean isuEmailVerified() {
		return uEmailVerified;
	}

	public void setuEmailVerified(boolean uEmailVerified) {
		this.uEmailVerified = uEmailVerified;
	}

	public boolean isuPhoneVerified() {
		return uPhoneVerified;
	}

	public void setuPhoneVerified(boolean uPhoneVerified) {
		this.uPhoneVerified = uPhoneVerified;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uPanNumber=" + uPanNumber + ", uName=" + uName + ", uUserName=" + uUserName
				+ ", uEmail=" + uEmail + ", uPhoneNo=" + uPhoneNo + ", uPassword=" + uPassword + ", uRole=" + uRole
				+ ", uAbout=" + uAbout + ", uProfilePic=" + uProfilePic + ", uEnabled=" + uEnabled + ", uEmailVerified="
				+ uEmailVerified + ", uPhoneVerified=" + uPhoneVerified + ", clients=" + clients + "]";
	}

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> uRoleList = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// List of roles [USER, ADMIN]
		// Collection of SimpleGrantedAuthority [roles{ADMIN, USER}]

		Collection<SimpleGrantedAuthority> roles = uRoleList.stream().map(uRole -> new SimpleGrantedAuthority(uRole))
				.collect(Collectors.toList());
		return roles;
	}

	// logic Login Credentials for Username
	@Override
	public String getUsername() {
		return this.uEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.uEnabled;
	}

	@Override
	public String getPassword() {
		return this.uPassword;
	}

}
