package com.orka.callbridge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orka.callbridge.entities.Client;
import com.orka.callbridge.entities.User;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

	List<Client> findByUser(User user);
	
	@Query("SELECT c FROM client c WHERE c.user.id = :userId")
	List<Client> findByUserUId(@Param("userId") String userId);

	
}
