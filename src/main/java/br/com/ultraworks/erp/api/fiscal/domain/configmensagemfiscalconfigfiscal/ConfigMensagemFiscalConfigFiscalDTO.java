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
	private String regimeTributarioNome;
	private String ufOrigemSigla;
	private String ufDestinoSigla;
	private String indicadorOperacao;
	private boolean icms;
	private boolean ipi;
	private boolean pis;
	private boolean cofins;

}
