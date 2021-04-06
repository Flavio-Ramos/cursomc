package com.flavioramos.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Cliente;
import com.flavioramos.cursomc.dto.ClienteDTO;
import com.flavioramos.cursomc.repositories.ClienteRepository;
import com.flavioramos.cursomc.service.exceptions.DataIntegrityException;
import com.flavioramos.cursomc.service.exceptions.ObjectNotFoundException;

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
	
	public Cliente update(Cliente obj) {
		Cliente newObj = this.find(obj.getId());
		this.updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		//TODO implementar marcar data de deleção
		this.find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),null,null);
	}
	
	public void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
