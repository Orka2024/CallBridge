package com.orka.callbridge.entities;

import javax.persistence.Table;
import java.lang.Override;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="cibilclient")
@Table(name="cibilclients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cibilclient {
	
	@Id
	private String clientId;
	
	@Column(nullable = false)
	private String clientname;
	
	@Column(length=13,nullable = false)
	private String clientnumber;
	
	@Column(unique = true,nullable = false)
	private String clientemail;
	
	@Column(length=10,nullable = false)
	private String clientpan;
	
	@Column(nullable = false)
	private String clientbod;
	
	@Column(nullable = false)
	private String clientaddress;
	
	@Column(nullable = false)
	private String clientpin;
	
	@Column(nullable = false)
	private String clientloanty;
	
	@Column(nullable = false)
	private String clientIncome;

	
	@Override
	public String toString() {
		return "Cibilclient [clientId=" + clientId + ", clientname=" + clientname + ", clientnumber=" + clientnumber
				+ ", clientemail=" + clientemail + ", clientpan=" + clientpan + ", clientbod=" + clientbod
				+ ", clientaddress=" + clientaddress + ", clientpin=" + clientpin + ", clientloanty=" + clientloanty
				+ ", clientIncome=" + clientIncome + "]";
	}
	
	

	

	
	

}
