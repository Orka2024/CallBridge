package com.orka.callbridge.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.orka.callbridge.entities.Client;
import com.orka.callbridge.entities.User;

public interface ClientService {

	Client save(Client client);

	void update(Client client);

	List<Client> getAll();

	Client getClientById(String cId);

	void delete(String cId);

	List<Client> search(String cName, String cEmail, String cPanNumber);

	List<Client> getByUserId(String uId);

	Page<Client> getByUser(User user, int page, int size, String sortField, String Direction);
	
	void save(MultipartFile file);
	
	String importClientsFromExcel(MultipartFile file);

}
