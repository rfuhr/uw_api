package br.com.ultraworks.erp.api.seguranca.domain.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PermissaoFuncionalidadeUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long funcionalidadeId;
	private boolean consultar;
	private boolean inserir;
	private boolean alterar;
	private boolean excluir;
	private boolean liberado;
	private boolean crud;
	private int order;
}
