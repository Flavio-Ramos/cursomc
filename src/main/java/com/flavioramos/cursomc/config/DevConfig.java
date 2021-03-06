package com.flavioramos.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.flavioramos.cursomc.services.DBService;
import com.flavioramos.cursomc.services.EmailService;
import com.flavioramos.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbService.instatiateTestDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
