package com.orka.callbridge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orka.callbridge.entities.Approvedocupload;

@Repository
public interface ApprovedocuploadRepo extends JpaRepository<Approvedocupload, String> {

	

} 
