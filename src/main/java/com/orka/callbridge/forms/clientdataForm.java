package com.orka.callbridge.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString 
public class clientdataForm {
	
	private String clientdata_id;
	private String client_name;
	private String client_contact;
	private String c_status;
	private String c_interest;

	
		
}
