package br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento;

import lombok.Data;

@Data
public class CondicaoPagamentoDTO {

	private Long id;

	private int codigo;
	private String nome;
	private String descricao;
	private String indicadorFormaPagamento;
	private boolean possuiEntrada;
	private Integer quantidadeParcelas;
	private String tipoCondicaoPagamento;
	private Integer diaVencimento;
	private Integer diasIntervalo;
	private String diasDivisao;
	private String composicao;

	private String indicadorFormaPagamentoNome;
	private String tipoCondicaoPagamentoNome;
}
