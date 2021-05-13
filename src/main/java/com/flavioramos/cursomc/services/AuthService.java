package com.flavioramos.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Cliente;
import com.flavioramos.cursomc.repositories.ClienteRepository;
import com.flavioramos.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random  = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		
		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i =0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opcao = random.nextInt(3);
		if(opcao == 0) {//gera um digito
			return (char) (random.nextInt(10) + 48);
		}else {
			if(opcao == 1) {//gera letra maiúscula
				return (char) (random.nextInt(26) + 65);
			}else {//gera letra minúscula
				return (char) (random.nextInt(26) + 97);
			}
		}
	}
}
