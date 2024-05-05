package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMensagemFiscalTipoIncentFiscalDTO {

	private Long id;
	private Long configMensagemFiscalId;
	private Long tipoIncentivoFiscalId;	
	private String tipoIncentivoFiscalNome;
	private int tipoIncentivoFiscalCodigo;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
