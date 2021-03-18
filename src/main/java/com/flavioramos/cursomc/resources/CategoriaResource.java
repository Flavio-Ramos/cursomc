package com.flavioramos.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flavioramos.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		
		Categoria cat1 = new Categoria("Informática",1);
		Categoria cat2 = new Categoria("Escritório",2);
		
		List<Categoria> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}

}
