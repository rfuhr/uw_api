package br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola;

import lombok.Data;

@Data
public class ConfigSistemaAgricolaDTO {

	private Long id;
	private Long configSistemaId;
	private Long tipoDocumentoRomaneioId;
	private Long operacaoInternaFixacaoId;
}
