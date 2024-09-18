package com.orka.callbridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orka.callbridge.dao.InterestedCustomerRepository;
import com.orka.callbridge.entities.InterestedCustomer;

@Service
public class InterestedCustomerService {

	@Autowired
    private InterestedCustomerRepository repository;
	
	// Save customer
    public InterestedCustomer saveCustomer(InterestedCustomer customer) {
        return repository.save(customer);
    }
    
}
