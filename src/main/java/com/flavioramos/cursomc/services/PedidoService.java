package com.flavioramos.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flavioramos.cursomc.domain.ItemPedido;
//import com.flavioramos.cursomc.domain.PagamentoComBoleto;
import com.flavioramos.cursomc.domain.Pedido;
import com.flavioramos.cursomc.domain.enums.EstadoPagamento;
import com.flavioramos.cursomc.repositories.ItemPedidoRepository;
import com.flavioramos.cursomc.repositories.PagamentoRepository;
import com.flavioramos.cursomc.repositories.PedidoRepository;
import com.flavioramos.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repor;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
//	@Autowired
//	private BoletoService boletoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repor.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado id " + id
				+ "Tipo " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		
//		if(obj.getPagamento() instanceof PagamentoComBoleto) {
//			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
//			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
//		}
		obj.getPagamento().preencher(obj.getInstante());
		obj = repor.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		//emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}
