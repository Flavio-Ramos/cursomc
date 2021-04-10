package com.flavioramos.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.flavioramos.cursomc.domain.enums.EstadoPagamento;
import com.flavioramos.cursomc.services.BoletoService;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,Date dataVencimento,Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public void preencher(Date instante) {
		BoletoService boletoService = new BoletoService();
		boletoService.preencherPagamentoComBoleto(this,instante);
	}
}
