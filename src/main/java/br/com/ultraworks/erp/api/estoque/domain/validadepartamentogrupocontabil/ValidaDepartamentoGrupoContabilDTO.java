package br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil;

import lombok.Data;

@Data
public class ValidaDepartamentoGrupoContabilDTO {

	private Long id;
	private Long departamentoId;
	private Long grupoContabilId;
	private boolean controlaEstoque;
	
	private String departamentoNome;
	private String grupoContabilNome;
	
}
