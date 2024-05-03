package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMensagemFiscalConfigFiscalDTO {

	private Long id;
	private Long configMensagemFiscalId;
	private Long configuracaoFiscalId;	
	private String configuracaoFiscalRegime;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
