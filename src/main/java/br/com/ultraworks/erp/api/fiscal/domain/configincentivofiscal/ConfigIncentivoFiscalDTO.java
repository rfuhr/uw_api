package br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfigIncentivoFiscalDTO {

	private Long id;
	private Long tipoIncentivoFiscalId;
	private String tipoIncentivoFiscalNome;
	private int tipoIncentivoFiscalCodigo;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@Valid
	private List<ConfigIncentivoFiscalParceiro> configIncentivoFiscalParceiros;
}
