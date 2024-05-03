package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMensagemFiscalSituacTribDTO {

	private Long id;
	private Long configMensagemFiscalId;
	private Long situacaoTributariaId;	
	private String situacaoTributariaTipoTributo;
	private String situacaoTributariaNome;
	private int situacaoTributariaCodigo;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
