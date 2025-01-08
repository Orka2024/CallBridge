package com.orka.callbridge.entities;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.Override;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="activelist")
@Table(name="activelist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Activelist {

	@Id
	private String cibilId;
	
	@Column(nullable = false)
	private String ClientActiveNumber;
	 
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE) // Ensures only the date part is saved in the database
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date activecaseDate;
    
	@Column(nullable = false)
	private int applyStatus;
	
	@Column(nullable = false)
	private String employeeName;
	
	@Column(nullable = false)
	private String clientName;
	
	@Column(length=13,nullable = false)
	private String clientNumber;
	
	@Column(unique = true,nullable = false)
	private String clientEmail;
	
	@Column(length=10,nullable = false)
	private String clientPan;
	
	@Column(nullable = false)
	private String clientBod;
	
	@Column(nullable = false)
	private String clientAddress;
	
	@Column(nullable = false)
	private String clientPin;
	
	@Column(nullable = false)
	private String clientLoanty;
	
	@Column(nullable = false)
	private String clientIncome;
	
	@Column(nullable = false)
	private String cibilScore;	

	@Column(nullable = false)
	private String cibilStatus;

	@Column(nullable = false)
	private String cibilReason;
	
	@ManyToOne
	@JoinColumn(name = "user_uid", referencedColumnName = "u_id")
	private User user;
	
	private String cibilUpload;
	
	private String cloudinaryImagePublicId;

	@Override
	public String toString() {
		return "Activelist [cibilId=" + cibilId + ", ClientActiveNumber=" + ClientActiveNumber + ", activecaseDate="
				+ activecaseDate + ", applyStatus=" + applyStatus + ", employeeName=" + employeeName + ", clientName="
				+ clientName + ", clientNumber=" + clientNumber + ", clientEmail=" + clientEmail + ", clientPan="
				+ clientPan + ", clientBod=" + clientBod + ", clientAddress=" + clientAddress + ", clientPin="
				+ clientPin + ", clientLoanty=" + clientLoanty + ", clientIncome=" + clientIncome + ", cibilScore="
				+ cibilScore + ", cibilStatus=" + cibilStatus + ", cibilReason=" + cibilReason + ", cibilUpload="
				+ cibilUpload + ", cloudinaryImagePublicId=" + cloudinaryImagePublicId + "]";
	}
	





	

}
