package com.orka.callbridge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orka.callbridge.dao.ActivelistRepo;
import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.service.ActivelistService;


@Service
public class ActivelistServiceImpl implements ActivelistService {
	
	@Autowired
    private ActivelistRepo activelistRepo;
	
	public Activelist saveActivelist(Activelist activelist) {
		return activelistRepo.save(activelist);
    }

	@Override
	public List<Activelist> getAll() {
		return activelistRepo.findAll() ;
	}

}
