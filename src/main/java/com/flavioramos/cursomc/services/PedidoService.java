package com.flavioramos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Pedido;
import com.flavioramos.cursomc.exceptions.ObjectNotFoundException;
import com.flavioramos.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repor;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repor.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado id " + id
				+ "Tipo " + Pedido.class.getName()));
	}
}
