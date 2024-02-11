package br.com.ultraworks.erp.api.organograma.domain.organizacao;

import lombok.Data;

@Data
public class OrganizacaoDTO {

	private Long id;
	private String nome;
	private String sigla;
	private String tenant;
}
