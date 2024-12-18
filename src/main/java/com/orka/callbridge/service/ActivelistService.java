package com.orka.callbridge.service;

import org.springframework.stereotype.Service;

import com.orka.callbridge.entities.Activelist;

@Service
public interface ActivelistService {
	
	Activelist saveActivelist(Activelist activelist);

}
