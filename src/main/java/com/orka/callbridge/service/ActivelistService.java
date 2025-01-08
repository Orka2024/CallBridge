package com.orka.callbridge.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.orka.callbridge.entities.Activelist;
import com.orka.callbridge.entities.User;


@Service
public interface ActivelistService {
	 
	Activelist saveActivelist(Activelist activelist);

	List<Activelist> getAll();
	
	Optional<Activelist> getActivelistById(String id);

    Page<Activelist> getByUser(User user, int page, int size, String sortField, String Direction);
}
