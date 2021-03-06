package com.flavioramos.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flavioramos.cursomc.domain.Categoria;
import com.flavioramos.cursomc.domain.Cidade;
import com.flavioramos.cursomc.domain.Cliente;
import com.flavioramos.cursomc.domain.Endereco;
import com.flavioramos.cursomc.domain.Estado;
import com.flavioramos.cursomc.domain.ItemPedido;
import com.flavioramos.cursomc.domain.Pagamento;
import com.flavioramos.cursomc.domain.PagamentoComBoleto;
import com.flavioramos.cursomc.domain.PagamentoComCartao;
import com.flavioramos.cursomc.domain.Pedido;
import com.flavioramos.cursomc.domain.Produto;
import com.flavioramos.cursomc.domain.enums.EstadoPagamento;
import com.flavioramos.cursomc.domain.enums.Perfil;
import com.flavioramos.cursomc.domain.enums.TipoCliente;
import com.flavioramos.cursomc.repositories.CategoriaRepository;
import com.flavioramos.cursomc.repositories.CidadeRepository;
import com.flavioramos.cursomc.repositories.ClienteRepository;
import com.flavioramos.cursomc.repositories.EnderecoRepository;
import com.flavioramos.cursomc.repositories.EstadoRepository;
import com.flavioramos.cursomc.repositories.ItemPedidoRepository;
import com.flavioramos.cursomc.repositories.PagamentoRepository;
import com.flavioramos.cursomc.repositories.PedidoRepository;
import com.flavioramos.cursomc.repositories.ProdutoRepository;


@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public void instatiateTestDataBase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrit??rio");
		Categoria cat3 = new Categoria(null, "Cama mes e banho");
		Categoria cat4 = new Categoria(null, "Eletr??nicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decora????o");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escrit??rio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Ro??adeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

//		Estado est1 = new Estado(null, "Minas Gerais");
//		Estado est2 = new Estado(null, "S??o Paulo");
// 
//		
//		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7));
//		produtoRepository.saveAll(Arrays.asList(p1, p2, p3,p4, p5, p6,p7, p8, p9,p10, p11));

		Estado estMG = new Estado(null, "Minas Gerais");
		Estado estSP = new Estado(null, "S??o Paulo");

		Cidade uberlandia = new Cidade(null, "Uberl??ndia", estMG);
		Cidade campinas = new Cidade(null, "Campinas", estSP);
		Cidade saoPaulo = new Cidade(null, "S??o Paulo", estSP);

		estMG.getCidades().addAll(Arrays.asList(uberlandia));
		estSP.getCidades().addAll(Arrays.asList(saoPaulo, campinas));

		estadoRepository.saveAll(Arrays.asList(estMG, estSP));
		cidadeRepository.saveAll(Arrays.asList(uberlandia, campinas, saoPaulo));

		Cliente cli1 = new Cliente(null, "Maria Silva", "prog_flavio@outlook.com", "363788912377", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("1111111111", "22222222222"));
		
		Cliente cli2 = new Cliente(null, "Carolina Ferraz", "clavedefaalterada@gmail.com", "37435025003", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("321"));
		cli2.getTelefones().addAll(Arrays.asList("333333333", "44444444444"));
		cli2.addPerfil(Perfil.ADMIN);
		//TODO adicionar uma verifica????o quando der erro no envio de emails
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300" ,"Apt 203" , "Jardim", "38220834",cli1, uberlandia);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105","Sala 800","Centro","38777012",cli1, saoPaulo);
		Endereco endereco3 = new Endereco(null, "Rua Floriano", "84",null,"Alameda ??sis","863482",cli2, uberlandia);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
		cli2.getEnderecos().addAll(Arrays.asList(endereco3));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2,endereco3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("31/03/2021 23:43"), endereco1,cli2);
		Pedido pedido2 = new Pedido(null, sdf.parse("29/03/2021 22:12"), endereco2,cli1);
		
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("29/03/2021 22:12"), null);
		pedido2.setPagamento(pagamento2);

		//cli1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		cli1.getPedidos().addAll(Arrays.asList(pedido2));
		cli2.getPedidos().addAll(Arrays.asList(pedido1));
		
		
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));
		
		
		ItemPedido item1 = new ItemPedido(pedido1, p1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(pedido1, p3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(pedido2, p2,100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(item1,item2));
		pedido2.getItens().addAll(Arrays.asList(item3));
		
		p1.getItens().addAll(Arrays.asList(item1));
		p3.getItens().addAll(Arrays.asList(item2));
		p2.getItens().addAll(Arrays.asList(item3));
		
		itemPedidoRepository.saveAll(Arrays.asList(item1,item2,item3));
	}
	
}
