package com.orka.callbridge.entities;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.Override;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	private String empname;
	
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
	
	
	@Column(nullable = false)
	private int ApplyStatus;
	
	
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE) // Ensures only the date part is saved in the database
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date TodayDate;


	@Override
	public String toString() {
		return "Cibilclient [clientId=" + clientId + ", empname=" + empname + ", clientname=" + clientname
				+ ", clientnumber=" + clientnumber + ", clientemail=" + clientemail + ", clientpan=" + clientpan
				+ ", clientbod=" + clientbod + ", clientaddress=" + clientaddress + ", clientpin=" + clientpin
				+ ", clientloanty=" + clientloanty + ", clientIncome=" + clientIncome + ", ApplyStatus=" + ApplyStatus
				+ ", TodayDate=" + TodayDate + "]";
	}








	
	
	





	
	

	

	
	

	

	
	

}
