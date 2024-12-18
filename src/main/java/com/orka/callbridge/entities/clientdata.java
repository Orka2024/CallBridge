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

@Entity(name="clientdata")
@Table(name="clientdata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class clientdata {

	@Id
	private String clientdata_id;
	 
	@Column(nullable = false)
	private String client_name;
	
	@Column(nullable = false)
	private String client_contact;
	
	@Column(length=50,nullable = false)
	private String c_status;
	
	@Column(length=50,nullable = false)
	private String c_interest;

	@Override
	public String toString() {
		return "DataIntres [clientdata_id=" + clientdata_id + ", client_name=" + client_name + ", client_contact="
				+ client_contact + ", c_status=" + c_status + ", c_interest=" + c_interest + "]";
	}
	
	

	

}
