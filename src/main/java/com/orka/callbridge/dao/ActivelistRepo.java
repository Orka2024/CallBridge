package com.orka.callbridge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.entities.User;

@Repository
public interface ActivelistRepo extends JpaRepository<Activelist, String> {

	
	
}
