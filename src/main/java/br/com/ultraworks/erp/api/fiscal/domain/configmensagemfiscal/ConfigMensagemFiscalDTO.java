package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
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
	
	@Valid
	private List<ConfigMensagemFiscalItem> configMensagemFiscalItems;
	
	@Valid
	private List<ConfigMensagemFiscalGrupoTrib> configMensagemFiscalGrupoTribs;
	
	@Valid
	private List<ConfigMensagemFiscalOperInterna> configMensagemFiscalOperInternas;
	
	@Valid
	private List<ConfigMensagemFiscalTipoIncentFiscal> configMensagemFiscalTipoIncentFiscals;
	
}
