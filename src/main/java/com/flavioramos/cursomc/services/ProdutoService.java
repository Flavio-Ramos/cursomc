package com.flavioramos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Categoria;
import com.flavioramos.cursomc.domain.Produto;
import com.flavioramos.cursomc.repositories.CategoriaRepository;
import com.flavioramos.cursomc.repositories.ProdutoRepository;
import com.flavioramos.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repor;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	public Produto find(Integer id) {
		
		Optional<Produto> obj = repor.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado id " + id
				+ "Tipo " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids); 
		//return repor.search(nome, categorias, pageRequest);
		return repor.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
