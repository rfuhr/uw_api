package br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial;

import lombok.Data;

@Data
public class TabelaPrecoEmpresaFilialDTO {

	private Long id;
	private Long tabelaPrecoId;
	private String tabelaPrecoNome;
	private int tabelaPrecoCodigo;
	private Long empresaFilialId;
	private String empresaFilialNome;
	private String empresaFilialSigla;

}
