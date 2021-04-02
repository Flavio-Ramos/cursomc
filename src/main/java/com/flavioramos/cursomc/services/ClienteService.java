package com.flavioramos.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Cliente;
import com.flavioramos.cursomc.exceptions.ObjectNotFoundException;
import com.flavioramos.cursomc.repositories.ClienteRepository;
import java.util.*;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, id - " + id 
				+ " Tipo " + Cliente.class.getName())); 

	}
}
