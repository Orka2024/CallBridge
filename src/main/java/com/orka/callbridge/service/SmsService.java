package com.orka.callbridge.service;

import org.springframework.stereotype.Service;

@Service
public interface SmsService {
		
	 public String sendSms(String phoneNumber, String msg);
	 
}