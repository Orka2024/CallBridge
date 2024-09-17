package com.orka.callbridge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "client")
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

	@Id
	private String cId;

	@Column(unique = true, nullable = false)
	private String cPanNumber;

	@Column(nullable = false)
	private String cName;

	private String cEmail;

	@Column(nullable = false)
	private String cPhoneNo;

	private String cStatus;

	private boolean cInterest = false;

	@Column(length = 5000)
	private String cDescription;

	@ManyToOne
	@JoinColumn(name = "user_u_id", referencedColumnName = "u_id")
	private User user;
}
