package com.orka.callbridge.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orka.callbridge.entities.Cibilclient;

@Repository
public interface CibilclientRepo extends JpaRepository<Cibilclient, String> {

	List<Cibilclient>findByEmpname(String empname);
	

}
