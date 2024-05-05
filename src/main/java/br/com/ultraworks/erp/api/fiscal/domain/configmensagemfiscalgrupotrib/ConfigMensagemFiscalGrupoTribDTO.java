package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMensagemFiscalGrupoTribDTO {

	private Long id;
	private Long configMensagemFiscalId;
	private Long grupoTributacaoId;	
	private String grupoTributacaoNome;
	private int grupoTributacaoCodigo;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
