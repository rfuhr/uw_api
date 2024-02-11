package br.com.ultraworks.erp.api.seguranca.domain.funcionalidade;

import lombok.Data;

@Data
public class FuncionalidadeDTO {

	private Long id;
	private String nome;
	private String tag;
	private boolean crud;
	
	private String moduloNome;
}
