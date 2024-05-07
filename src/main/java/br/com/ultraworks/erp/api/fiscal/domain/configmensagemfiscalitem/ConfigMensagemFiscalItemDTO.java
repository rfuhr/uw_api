package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMensagemFiscalItemDTO {

	private Long id;
	private Long configMensagemFiscalId;
	private Long itemId;	
	private String itemNome;
	private int itemCodigo;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
