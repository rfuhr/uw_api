package br.com.ultraworks.erp.api.agricola.domain.balanca;

import lombok.Data;

@Data
public class BalancaDTO {

	private Long id;

	private Long empresaFilialId;
	private String nome;
	private String porta;
	private int velocidade;
	private String empresaFilialNome;
}
