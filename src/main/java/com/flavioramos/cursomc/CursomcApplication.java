package com.flavioramos.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flavioramos.cursomc.domain.Categoria;
import com.flavioramos.cursomc.domain.Cidade;
import com.flavioramos.cursomc.domain.Cliente;
import com.flavioramos.cursomc.domain.Endereco;
import com.flavioramos.cursomc.domain.Estado;
import com.flavioramos.cursomc.domain.Pagamento;
import com.flavioramos.cursomc.domain.PagamentoComBoleto;
import com.flavioramos.cursomc.domain.PagamentoComCartao;
import com.flavioramos.cursomc.domain.Pedido;
import com.flavioramos.cursomc.domain.Produto;
import com.flavioramos.cursomc.domain.enums.EstadoPagamento;
import com.flavioramos.cursomc.domain.enums.TipoCliente;
import com.flavioramos.cursomc.repositories.CategoriaRepository;
import com.flavioramos.cursomc.repositories.CidadeRepository;
import com.flavioramos.cursomc.repositories.ClienteRepository;
import com.flavioramos.cursomc.repositories.EnderecoRepository;
import com.flavioramos.cursomc.repositories.EstadoRepository;
import com.flavioramos.cursomc.repositories.PagamentoRepository;
import com.flavioramos.cursomc.repositories.PedidoRepository;
import com.flavioramos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		
		Produto computador = new Produto(null, "Computador", 2000.00);
		Produto impressora = new Produto(null, "Impressora", 800.00);
		Produto mouser = new Produto(null, "Mouser", 80.00);
		
		informatica.getProduto().addAll(Arrays.asList(computador, impressora, mouser));
		escritorio.getProduto().addAll(Arrays.asList(impressora));
		
		computador.getCategoria().addAll(Arrays.asList(informatica));
		impressora.getCategoria().addAll(Arrays.asList(informatica, escritorio));
		mouser.getCategoria().addAll(Arrays.asList(informatica));
		
		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
		produtoRepository.saveAll(Arrays.asList(computador, impressora, mouser));

		Estado estMG = new Estado(null, "Minas Gerais");
		Estado estSP = new Estado(null, "São Paulo");

		Cidade uberlandia = new Cidade(null, "Uberlândia", estMG);
		Cidade campinas = new Cidade(null, "Campinas", estSP);
		Cidade saoPaulo = new Cidade(null, "São Paulo", estSP);

		estMG.getCidades().addAll(Arrays.asList(uberlandia));
		estSP.getCidades().addAll(Arrays.asList(saoPaulo, campinas));

		estadoRepository.saveAll(Arrays.asList(estMG, estSP));
		cidadeRepository.saveAll(Arrays.asList(uberlandia, campinas, saoPaulo));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "363788912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("1111111111", "22222222222"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300" ,"Apt 203" , "Jardim", "38220834",cli1, uberlandia);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105","Sala 800","Centro","38777012",cli1, saoPaulo);
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("31/03/2021 23:43"), endereco1,cli1);
		Pedido pedido2 = new Pedido(null, sdf.parse("29/03/2021 22:12"), endereco2,cli1);
		
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("29/03/2021 22:12"), null);
		pedido2.setPagamento(pagamento2);

		cli1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		
		
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));
	}
}
