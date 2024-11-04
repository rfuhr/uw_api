package br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria;

import java.util.List;

import lombok.Data;

@Data
public class ConfigAutorizacaoSolicitacaoMercadoriaDTO {

	private Long id;
	private Long empresaId;
	private Long empresaFilialId;
	private Long departamentoId;
	private Long grupoContabilId;
	private List<Long> autorizadoresId;

	private String empresaNome;
	private String empresaFilialNome;
	private String departamentoNome;
	private String grupoContabilNome;
}
