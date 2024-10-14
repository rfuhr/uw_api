package br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CalculoDescontoContratoRequest {

	private Long contratoAgricolaId;
	private Long departamentoId;
	private Long operacaoInternaId;
	private Long grupoOperacaoAgricolaId;
	private Long regraAtividadeId;
	private Long itemId;
	private LocalDate dataBase;
	private BigDecimal quantidadeContratada;
	private BigDecimal valorBruto;
}
