package com.orka.callbridge.forms;


import java.lang.Override;
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
public class Cibilclientform {
	
	private String clientname;
	private String clientnumber;
	private String clientemail;
	private String clientpan;
	private String clientbod;
	private String clientaddress;
	private String clientpin;
	private String clientloanty;
	private String clientIncome;
	

    // Capitalize the first letter of the name
    public void setClientname(String clientname) {
        this.clientname = capitalizeFirstLetter(clientname);
    }

    // Helper method to capitalize the first letter of the name
    private String capitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
    

    // Ensure PAN number is always uppercase
    public void setClientpan(String clientpan) {
        this.clientpan = clientpan != null ? clientpan.toUpperCase() : null;
    }


	 // Ensure email is always lowercase
    public void setClientemail(String clientemail) {
        this.clientemail = clientemail != null ? clientemail.toLowerCase() : null;
    }
	

	
	
}
