package com.orka.callbridge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orka.callbridge.dao.CibilclientRepo;
import com.orka.callbridge.entities.Cibilclient;
import com.orka.callbridge.helper.ResourceNotFoundException;
import com.orka.callbridge.service.CibilclientService;

@Service
public class CibilclientServiceImpl implements CibilclientService  {

	@Autowired
	private CibilclientRepo cibilclientRepo;
	
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	
	@Override
	public Cibilclient saveCibilclient(Cibilclient cibilclient) {
		String clientId=UUID.randomUUID().toString();
		cibilclient.setClientId(clientId);
		return cibilclientRepo.save(cibilclient);
	}

	@Override
	public Optional<Cibilclient> getCibilclientById(String id) {
		return cibilclientRepo.findById(id);
	}

	@Override
	public Optional<Cibilclient> updateCibilclient(Cibilclient cibilclient) {
		
		Cibilclient cibilclient1= cibilclientRepo.findById(cibilclient.getClientId()).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
		cibilclient1.setClientname(cibilclient.getClientname());
		cibilclient1.setClientnumber(cibilclient.getClientnumber());
		cibilclient1.setClientemail(cibilclient.getClientemail());
		cibilclient1.setClientpan(cibilclient.getClientpan());
		cibilclient1.setClientbod(cibilclient.getClientbod());
		cibilclient1.setClientaddress(cibilclient.getClientaddress());
		cibilclient1.setClientpin(cibilclient.getClientpin());
		cibilclient1.setClientloanty(cibilclient.getClientloanty());
		cibilclient1.setClientIncome(cibilclient.getClientIncome());
			
		Cibilclient save=cibilclientRepo.save(cibilclient1);
		
		return Optional.ofNullable(save);
	}

	@Override
	public void deleteCibilclient(String id) {
		Cibilclient cibilclient1= cibilclientRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
		 cibilclientRepo.delete(cibilclient1);
	}

	@Override
	public List<Cibilclient> getAll() {		
		return cibilclientRepo.findAll();
	}

	@Override
	public List<Cibilclient> findByEmpname(String empname) {
		// TODO Auto-generated method stub
		return  cibilclientRepo.findByEmpname(empname);
	}

	
}
