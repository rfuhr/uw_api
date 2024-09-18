package br.com.ultraworks.erp.api.financeiro.domain.conta;

import lombok.Data;

@Data
public class ContaDTO {

	private Long id;
	private Long agenciaId; 
	private String agenciaNome;
	private String bancoNome;
	private String dv;
	private String numero;
	private String nome;
	private Long tipoContaCxBcoId;
}
