package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfigMensagemFiscalDTO {

	private Long id;
	private Long mensagemFiscalId;
	private String mensagemFiscalNome;
	private int mensagemFiscalCodigo;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@Valid
	private List<ConfigMensagemFiscalConfigFiscal> configMensagemFiscalConfigFiscals;
	
	@Valid
	private List<ConfigMensagemFiscalSituacTrib> configMensagemFiscalSituacTribs;
	
}
