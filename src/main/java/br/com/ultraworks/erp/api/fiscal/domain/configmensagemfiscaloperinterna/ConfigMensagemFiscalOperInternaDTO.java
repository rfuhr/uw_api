package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMensagemFiscalOperInternaDTO {

	private Long id;
	private Long configMensagemFiscalId;
	private Long operacaoInternaId;	
	private String operacaoInternaNome;
	private String operacaoInternaSigla;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
