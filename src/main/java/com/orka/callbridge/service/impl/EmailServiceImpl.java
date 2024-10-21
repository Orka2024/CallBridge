package com.orka.callbridge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.orka.callbridge.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender emialSender;
	
	@Value("${spring.mail.properties.domain_name}")
	private String SenderName;
	
	@Override
	public void sendEmail(String to, String subject, String body) {
		
		SimpleMailMessage message =	new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		message.setFrom(SenderName);
		emialSender.send(message);
		System.out.println("service file");
	}


	
	

}
