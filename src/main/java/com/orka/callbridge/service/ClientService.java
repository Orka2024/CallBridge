package com.orka.callbridge.service;

import java.util.List;

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

	List<Client> getByUser(User user);
}
