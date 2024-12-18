package com.orka.callbridge.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orka.callbridge.dao.Dataintrested;
import com.orka.callbridge.entities.clientdata;
import com.orka.callbridge.service.DataintresService;

@Service
public class DataintersServiceImpl implements DataintresService  {

	@Autowired
	private Dataintrested dataintrested;
	
	@Override
	public Optional<clientdata> getclientdataById(String id) {
		return dataintrested.findById(id);
	}
	
	@Override
	public List<clientdata> getAll() {
		return dataintrested.findAll();
	}




	


	
}
