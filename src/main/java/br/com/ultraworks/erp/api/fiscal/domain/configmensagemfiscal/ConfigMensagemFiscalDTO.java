package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTribDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItemDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInternaDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTribDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscalDTO;
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
	private List<ConfigMensagemFiscalConfigFiscalDTO> configMensagemFiscalConfigFiscals;
	
	@Valid
	private List<ConfigMensagemFiscalSituacTribDTO> configMensagemFiscalSituacTribs;
	
	@Valid
	private List<ConfigMensagemFiscalItemDTO> configMensagemFiscalItems;
	
	@Valid
	private List<ConfigMensagemFiscalGrupoTribDTO> configMensagemFiscalGrupoTribs;
	
	@Valid
	private List<ConfigMensagemFiscalOperInternaDTO> configMensagemFiscalOperInternas;
	
	@Valid
	private List<ConfigMensagemFiscalTipoIncentFiscalDTO> configMensagemFiscalTipoIncentFiscals;
	
}
