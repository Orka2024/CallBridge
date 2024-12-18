package com.orka.callbridge.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.orka.callbridge.dao.ClientRepository;
import com.orka.callbridge.entities.Client;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.helper.AppConstants;
import com.orka.callbridge.helper.Helper;
import com.orka.callbridge.helper.ResourceNotFoundException;
import com.orka.callbridge.service.ClientService;
import com.orka.callbridge.service.UserService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserService userService;

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
	
	public void save(MultipartFile file) {
		try {
			List<Client> clients = Helper.convertExcelToListOfProduct(file.getInputStream());
			this.clientRepository.saveAll(clients);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String importClientsFromExcel(MultipartFile file) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = Helper.getEmailOfSignedInUser(authentication);
		User userByUId = userService.getUserByEmail(username);
        
		List<Client> clients = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Skip header row
                if (row.getRowNum() == 0) continue;

                Client client = new Client();
                client.setCId(UUID.randomUUID().toString());  // Generate a random UUID              
                client.setCName(getCellValue(row.getCell(1)));
                client.setCPhoneNo(getCellValue(row.getCell(2)));
                //client.setCEmail(getCellValue(row.getCell(3)));
                //client.setCStatus(getCellValue(row.getCell(4)));
                //client.setCLastDateContacted(getCellValue(row.getCell(5)));
                client.setCCloudinaryImagePublicId( UUID.randomUUID().toString());
                client.setCImage(AppConstants.DEFAULT_PROFILE_IMAGE_ID);
                client.setUser(userByUId);
                clients.add(client);
            }

            clientRepository.saveAll(clients);
            return "Clients imported successfully";

        } catch (IOException e) {
            return "Error processing the file: " + e.getMessage();
        } catch (Exception e) {
            return "Error importing clients: " + e.getMessage();
        }
	}
	private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue()); // Convert to long for phone numbers
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }
	
}
