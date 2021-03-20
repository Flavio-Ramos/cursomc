package com.flavioramos.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Categoria;
import com.flavioramos.cursomc.repositories.CategoriaRepository;
import java.util.*;
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		//Categoria obj = repo.findOne(id);
		//return obj;
		
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElse(null); 

	}
}
