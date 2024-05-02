package br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.validador.ValidaTipoTributo;
import lombok.Data;

@Data
public class ConfigIncentivoFiscalParceiroDTO {

	private Long id;
	private Long configIncentivoFiscalId;
	private Long parceiroLocalId;	
	private String parceiroNomeRazaoSocial;
	private String parceiroCnpjCpf;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	@ValidaTipoTributo
	private String tipoTributo;
	private Long ufId;
	private String ufNome;
	private String ufSigla;
	private BigDecimal percentualAproveitamento;

}
