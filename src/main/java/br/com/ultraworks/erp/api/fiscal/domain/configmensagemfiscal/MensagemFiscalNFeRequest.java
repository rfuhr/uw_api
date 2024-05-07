package br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class MensagemFiscalNFeRequest {
	
	private Long parceiroLocalId;
	private Long operacaoInternaId;
	private LocalDate data;
	private List<Long> listaItens;
	private List<Long> listaConfiguracaoICMS;
	private List<Long> listaConfiguracaoIPI;
	private List<Long> listaConfiguracaoPIS;
	private List<Long> listaConfiguracaoCOFINS;

}
