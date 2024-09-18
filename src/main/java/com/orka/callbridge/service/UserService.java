package com.orka.callbridge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.orka.callbridge.entities.User;

@Service
public interface UserService {

	User saveUser(User user);

	Optional<User> getUserById(String uId);

	Optional<User> updateUser(User user);

	// Optional<User> findByuPanNumber(String uPanNumber);

	void deleteUser(String uId);

	boolean isUserExist(String uId);

	boolean isUserExistByUserName(String uUserName);

	boolean isUserExistByEmail(String uEmail);

	List<User> getAllUsers();

} // Methods related to User Service [Logic]
