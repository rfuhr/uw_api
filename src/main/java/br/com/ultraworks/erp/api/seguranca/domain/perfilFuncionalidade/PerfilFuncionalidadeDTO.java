package br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade;

import lombok.Data;

@Data
public class PerfilFuncionalidadeDTO {

	private Long id;
	private Long perfilId;
	private Long funcionalidadeId;

	private boolean consultar;
	private boolean inserir;
	private boolean alterar;
	private boolean excluir;
}
