package com.flavioramos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flavioramos.cursomc.domain.Categoria;
import com.flavioramos.cursomc.domain.Cidade;
import com.flavioramos.cursomc.domain.Estado;
import com.flavioramos.cursomc.domain.Produto;
import com.flavioramos.cursomc.repositories.CategoriaRepository;
import com.flavioramos.cursomc.repositories.CidadeRepository;
import com.flavioramos.cursomc.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		/*
		 * Categoria musica = new Categoria(null, "Música"); Categoria esportes = new
		 * Categoria(null, "Esportes"); Categoria limpeza = new Categoria(null,
		 * "Limpeza"); Categoria escola = new Categoria(null, "Escola");
		 */
		Produto computador = new Produto(null, "Computador", 2000.00);
		Produto impressora = new Produto(null, "Impressora", 800.00);
		Produto mouser = new Produto(null, "Mouser", 80.00);
		/*
		 * Produto caderno = new Produto(null, "Caderno", 35.00); Produto caneta = new
		 * Produto(null, "Caneta", 3.00); Produto guitarra = new Produto(null,
		 * "Guitarra", 2500.00); Produto bateria = new Produto(null, "Bateria",
		 * 5200.00); Produto vassoura = new Produto(null, "Vassoura", 15.00); Produto
		 * sabao = new Produto(null, "Sabão", 7.00); Produto teclado = new Produto(null,
		 * "Teclado", 350.00); Produto bola = new Produto(null, "Bola", 95.00); Produto
		 * redeDeVolei = new Produto(null, "Rede de Volêi", 74.00);
		 */

		informatica.getProduto().addAll(Arrays.asList(computador, impressora, mouser));
		escritorio.getProduto().addAll(Arrays.asList(impressora));
		/*
		 * musica.getProduto().addAll(Arrays.asList(guitarra, bateria, teclado));
		 * esportes.getProduto().addAll(Arrays.asList(redeDeVolei, bola));
		 * limpeza.getProduto().addAll(Arrays.asList(sabao, vassoura));
		 * escola.getProduto().addAll(Arrays.asList(caderno, caneta));
		 */

		computador.getCategoria().addAll(Arrays.asList(informatica));
		impressora.getCategoria().addAll(Arrays.asList(informatica, escritorio));
		mouser.getCategoria().addAll(Arrays.asList(informatica));
		/*
		 * caderno.getCategoria().addAll(Arrays.asList(escola));
		 * caneta.getCategoria().addAll(Arrays.asList(escola,Esritorio));
		 * guitarra.getCategoria().addAll(Arrays.asList(musica));
		 * bateria.getCategoria().addAll(Arrays.asList(musica));
		 * vassoura.getCategoria().addAll(Arrays.asList(limpeza));
		 * sabao.getCategoria().addAll(Arrays.asList(limpeza));
		 * teclado.getCategoria().addAll(Arrays.asList(informatca,musica));
		 * bola.getCategoria().addAll(Arrays.asList(esportes));
		 * redeDeVolei.getCategoria().addAll(Arrays.asList(esportes));
		 */
		/*
		 * categoriaRepository.saveAll(Arrays.asList(informatca,
		 * Esritorio,musica,esportes,limpeza,escola));
		 * produtoRepository.saveAll(Arrays.asList(computador, impressora,
		 * mouser,caderno,caneta,guitarra,bateria,vassoura,sabao,teclado,bola,
		 * redeDeVolei));
		 */

		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
		produtoRepository.saveAll(Arrays.asList(computador, impressora, mouser));
		
		Estado estMG = new Estado(null,"Minas Gerais");
		Estado estSP = new Estado(null, "São Paulo");
		
		Cidade uberlandia = new Cidade(null, "Uberlândia",estMG);
		Cidade campinas = new Cidade(null,"Campinas",estSP);
		Cidade saoPaulo = new Cidade(null, "São Paulo", estSP);
		
		estMG.getCidades().addAll(Arrays.asList(uberlandia));
		estSP.getCidades().addAll(Arrays.asList(saoPaulo,campinas));
		
		estadoRepository.saveAll(Arrays.asList(estMG,estSP));
		cidadeRepository.saveAll(Arrays.asList(uberlandia,campinas,saoPaulo));

	}
}
