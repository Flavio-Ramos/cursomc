package com.flavioramos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Categoria;
import com.flavioramos.cursomc.repositories.CategoriaRepository;
import com.flavioramos.cursomc.service.exceptions.ObjectNotFoundException;
import com.flavioramos.cursomc.service.exceptions.DataIntegrityException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		// Categoria obj = repo.findOne(id);
		// return obj;
		//TODO verificar se não foi deletado
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, id - " + id 
				+ " Tipo " + Categoria.class.getName())); 

	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		this.find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		//TODO implementar marcar data de deleção
		this.find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que existe produtos!");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
}
