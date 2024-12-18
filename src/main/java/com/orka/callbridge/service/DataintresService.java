package com.orka.callbridge.service;

import java.util.List;
import java.util.Optional;

import com.orka.callbridge.entities.clientdata;




public interface DataintresService {
	
	Optional<clientdata> getclientdataById(String id);

	
	List<clientdata> getAll();
	
	
	
	
	


}
