package com.orka.callbridge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name field is required")
	@Size(min = 3, max = 20, message = "Name must be between 3 - 20 characters")
	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotBlank(message = "Password field is required")
	@Size(min = 5, message = "Password must be at least 5 characters")
	// Add more constraints for password strength if needed
	private String password;

	private String role;
	private boolean enabled;
	private String imageUrl;

	@Column(length = 1000)
	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about + "]";
	}

}
