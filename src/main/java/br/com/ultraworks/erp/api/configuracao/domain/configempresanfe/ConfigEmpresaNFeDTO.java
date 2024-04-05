package br.com.ultraworks.erp.api.configuracao.domain.configempresanfe;

import lombok.Data;

@Data
public class ConfigEmpresaNFeDTO {

	private Long id;

	private Long configEmpresaId;
	private Long empresaFilialId;
	private String tipoCertificado;
	private String tipoAmbiente;
	private String tipoEmissao;
	private int serie;
}
