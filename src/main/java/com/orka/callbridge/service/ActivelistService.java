package com.orka.callbridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.entities.Cibilclient;

@Service
public interface ActivelistService {
	
	Activelist saveActivelist(Activelist activelist);

	List<Activelist> getAll();

}
