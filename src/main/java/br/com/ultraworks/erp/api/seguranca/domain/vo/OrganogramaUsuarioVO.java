package br.com.ultraworks.erp.api.seguranca.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrganogramaUsuarioVO {

	private long empresaId;
	private String empresaNome;
	private long empresaFilialId;
	private String empresaFilialNome;
}