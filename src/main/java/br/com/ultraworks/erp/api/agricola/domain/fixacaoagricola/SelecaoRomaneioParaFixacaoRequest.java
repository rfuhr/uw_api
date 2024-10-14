package br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SelecaoRomaneioParaFixacaoRequest {
	
	private Long empresaFilialId;
	private Long departamentoId;
	private Long parceiroLocalId;
	private Long itemId;
	private Long operacaoInternaFixacaoId;
	private Long grupoOperacaoAgricolaId;
	private Long tipoPrecoAgricolaId;
	private LocalDate dataDocumento;
	private LocalDate dataVencimento;
	private Long contratoAgricolaId;
}
