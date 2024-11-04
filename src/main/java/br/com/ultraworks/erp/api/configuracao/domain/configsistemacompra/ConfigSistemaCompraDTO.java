package br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra;

import lombok.Data;

@Data
public class ConfigSistemaCompraDTO {

	private Long id;
	private Long configSistemaId;
	private Long tipoDocumentoSolicitacaoId;
	private Long tipoDocumentoCotacaoId;
}
