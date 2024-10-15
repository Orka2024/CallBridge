package com.orka.callbridge.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.orka.callbridge.dao.ClientRepository;
import com.orka.callbridge.entities.Client;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.helper.ResourceNotFoundException;
import com.orka.callbridge.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client save(Client client) {

		String cId = UUID.randomUUID().toString();
		client.setCId(cId);
		return clientRepository.save(client);
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(String cId) {
		return clientRepository.findById(cId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found with given cId: " + cId));
	}

	@Override
	public void delete(String cId) {
		var client = clientRepository.findById(cId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found with given cId: " + cId));
		clientRepository.delete(client);
	}

	@Override
	public List<Client> search(String cName, String cEmail, String cPanNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getByUserId(String uId) {
		return clientRepository.findByUserUId(uId);
	}

	@Override
	public Page<Client> getByUser(User user, int page, int size, String sortBy, String direction) {
		

		Sort sort = direction.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		var pageable = PageRequest.of(page, size,sort);
		
		
		return clientRepository.findByUser(user, pageable);
	}

}
