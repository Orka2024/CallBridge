package com.orka.callbridge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orka.callbridge.config.TwilioConfig;
import com.orka.callbridge.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsServiceImpl implements SmsService{

	private final TwilioConfig twilioConfig;
	
    @Autowired
    public SmsServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }
	 
	@Override
	public String sendSms(String phoneNumber, String msg) {
		 PhoneNumber to = new PhoneNumber(phoneNumber);
	        PhoneNumber from = new PhoneNumber(twilioConfig.getTwilioPhoneNumber());

	        Message message = Message.creator(to, from, msg).create();
	        System.out.println("SMS sent successfully: " + message.getSid());
		return "SMS sent successfully";
	}
}
