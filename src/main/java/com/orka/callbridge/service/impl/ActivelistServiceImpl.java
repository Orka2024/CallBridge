package com.orka.callbridge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.orka.callbridge.dao.ActivelistRepo;
import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.entities.User;
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
	
	@Override
	public Optional<Activelist> getActivelistById(String id) {
		return activelistRepo.findById(id);
	}

	@Override
	public Page<Activelist> getByUser(User user, int page, int size, String sortBy, String direction) {
		Sort sort = direction.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		var pageable = PageRequest.of(page, size,sort);
		return activelistRepo.findByUser(user, pageable);
		
	}




}
