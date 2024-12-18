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

@Entity(name="upload")
@Table(name="upload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class upload {

	@Id
	private String cibilId;
	 
	@Column(nullable = false)
	private String empName;
	
	@Override
	public String toString() {
		return "upload [cibilId=" + cibilId + ", empName=" + empName + "]";
	}



}
