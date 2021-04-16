package com.flavioramos.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flavioramos.cursomc.domain.Cliente;
import com.flavioramos.cursomc.repositories.ClienteRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente clie = repo.findByEmail(email);
		if(clie == null) {
			throw new UsernameNotFoundException(email); 
		}
		
		return null;
	}

}
