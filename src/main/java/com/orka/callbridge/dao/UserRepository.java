package com.orka.callbridge.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.orka.callbridge.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUId(String uId);

	//Optional<User> findByUUserName(String uUserName);

	Optional<User> findByUEmail(String uEmail);

	Optional<User> findByUEmailAndUPassword(String uEmail, String uPassword);

}
