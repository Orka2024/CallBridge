package com.orka.callbridge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orka.callbridge.dao.UserRepository;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.helper.AppConstants;
import com.orka.callbridge.helper.ResourceNotFoundException;
import com.orka.callbridge.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public User saveUser(User user) {
		String uId = UUID.randomUUID().toString();
		user.setuId(uId);

		// Encoding Password
		// user.setuProfilePic(uId);
		user.setuPassword(passwordEncoder.encode(user.getPassword()));

		// Set User Role
		user.setURoleList(List.of(AppConstants.ROLE_USER));

		// logger.info(user.getProvider().toString()); //problem
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(String uId) {
		return userRepository.findById(uId);
	}

	/*
	 * @Override public Optional<User> findByUPanNumber(String uPanNumber) { //
	 * Check if uPanNumber is null or empty before querying if (uPanNumber == null
	 * || uPanNumber.trim().isEmpty()) { return user; }
	 * 
	 * // Use the repository method to find a user by uPanNumber User user =
	 * userRepository.findByuPanNumber(uPanNumber).orElse(null);
	 * 
	 * // Return true if user is found, otherwise false return user != null; }
	 */

	@Override
	public Optional<User> updateUser(User user) {
		User userNew = userRepository.findById(user.getuId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userNew.setuName(user.getuName());
		userNew.setuEmail(user.getuEmail());
		userNew.setuPhoneNo(user.getuPhoneNo());
		userNew.setuPanNumber(user.getuPanNumber());
		userNew.setuRole(user.getuRole());
		userNew.setuUserName(user.getuUserName());
		userNew.setuPassword(user.getuPassword());
		userNew.setuAbout(user.getuAbout());
		userNew.setuProfilePic(user.getuProfilePic());
		userNew.setuEnabled(user.isuEnabled());
		userNew.setuEmailVerified(user.isuEmailVerified());
		userNew.setuPhoneVerified(user.isuPhoneVerified());

		// Save the user in database
		User save = userRepository.save(userNew);
		return Optional.ofNullable(save);
	}

	@Override
	public void deleteUser(String uId) {
		User userNew = userRepository.findByUId(uId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(userNew);

	}

	@Override
	public boolean isUserExist(String uId) {
		User userNew = userRepository.findById(uId).orElse(null);
		return userNew != null ? true : false;
	}

	/*
	 * @Override public boolean isUserExistByUserName(String uUserName) { User
	 * userNew = userRepository.findByUUserName(uUserName).orElse(null); return
	 * userNew != null ? true : false; }
	 */

	@Override
	public boolean isUserExistByEmail(String uEmail) {
		User user = userRepository.findByUEmail(uEmail).orElse(null);
		return user != null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String uEmail) {
		return userRepository.findByUEmail(uEmail).orElse(null);
	}

}
