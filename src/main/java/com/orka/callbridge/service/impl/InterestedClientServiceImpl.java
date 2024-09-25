package com.orka.callbridge.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.orka.callbridge.dao.InterestedClientRepository;
//import com.orka.callbridge.entities.InterestedClient;
//import com.orka.callbridge.forms.InterestedClientForm;
//import com.orka.callbridge.helper.ResourceNotFoundException;
//import com.orka.callbridge.service.InterestedClientService;
//
//@Service  //("interestedClientService")
//public class InterestedClientServiceImpl implements InterestedClientService {
//
//	@Autowired
//	private InterestedClientRepository interestedClientRepository;
//	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@Override
//	public InterestedClient saveInterestedClient(InterestedClient interestedClient) {
//		
//		String id = UUID.randomUUID().toString();
//		interestedClient.setId(id);
//		return interestedClientRepository.save(interestedClient);		
//	}
//	
//	@Override
//	public Optional<InterestedClient> getInterestedClientById(String id) {
//		return interestedClientRepository.findById(id);
//	}
//
//	@Override
//	public Optional<InterestedClient> updateInterestedClient(InterestedClient interestedClient) {
//		InterestedClient interestedClientNew = interestedClientRepository.findById(interestedClient.getId())
//		.orElseThrow(() -> new ResourceNotFoundException("User not found"));
//		interestedClientNew.setName(interestedClient.getName());
//		interestedClientNew.setPhone(interestedClient.getPhone());
//		InterestedClient save = interestedClientRepository.save(interestedClientNew);
//		return Optional.ofNullable(save);
//	}
//
//	@Override
//	public List<InterestedClient> getAllInterestedClients() {
//		return interestedClientRepository.findAll();
//	}
//
//	
//	@Transactional
//	public void addInterestedClient(InterestedClient interestedClient) {
//		interestedClientRepository.saveAndFlush(interestedClient);		
//
//		
//	}
//
//	@Transactional
//	public void addInterestedClient(InterestedClientForm interestedClientForm) {
//		interestedClientRepository.saveAndFlush(interestedClientForm);		
//	}
//
//}
