package com.orka.callbridge.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

import com.orka.callbridge.entities.Client;

public class Helper {

	public static String getEmailOfSignedInUser(Authentication authentication) {

		// AuthenticationPrincipal principal = (AuthenticationPrincipal)
		// authentication.getPrincipal();

		if (authentication instanceof OAuth2AuthenticationToken) {

			var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			String authorizedClientRegistrationId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

			return "";

		} else {

			System.out.println("Getting Data from Local Database ");
			return authentication.getName();
		}

	}
	
	//Check if the file is in excel format
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//convert excel to list of products
	public static List<Client> convertExcelToListOfProduct(InputStream is){
		List<Client> list=new ArrayList<>();
		try {
			
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			
			XSSFSheet sheet = xssfWorkbook.getSheet("Sheet1");
			
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			
			while(iterator.hasNext()) {
				Row row = iterator.next();
				if(rowNumber==0) {
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cells = row.iterator();
				int cId=0;
				
				Client c = new Client();
				
				while(cells.hasNext())
				{
					
					Cell cell = cells.next();
					
					switch(cId) {
					case 0:
						c.setCId(UUID.randomUUID().toString());
						break;
					case 1:
						c.setCName(cell.getStringCellValue());
						break;
					case 2:
						c.setCPhoneNo(cell.getStringCellValue());
						break;
					case 3:
						c.setCEmail(cell.getStringCellValue());
						break;
					case 4:
						c.setCStatus(cell.getStringCellValue());
						break;
					case 5:
						c.setCLastDateContacted(cell.getStringCellValue());
						break;
					default:
						break;
						
					}
					cId++;
					
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
