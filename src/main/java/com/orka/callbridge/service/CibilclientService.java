package com.orka.callbridge.service;

import java.util.Optional;

import com.orka.callbridge.entities.Cibilclient;

public interface CibilclientService {

	Cibilclient saveCibilclient(Cibilclient cibilclient);
	
	Optional<Cibilclient> getCibilclientById(String id);
	
	Optional<Cibilclient> updateCibilclient(Cibilclient cibilclient);
	
	void deleteCibilclient(String id);

}
