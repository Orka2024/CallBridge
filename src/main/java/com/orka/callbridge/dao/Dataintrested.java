package com.orka.callbridge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.entities.clientdata;

@Repository
public interface Dataintrested extends JpaRepository<clientdata, String> {

	
	
}
