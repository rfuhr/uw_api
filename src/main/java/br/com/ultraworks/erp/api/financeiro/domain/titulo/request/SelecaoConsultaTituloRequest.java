package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelecaoConsultaTituloRequest {

	private Long tipoTituloId;
	private Long empresaFilialId;
	private Long departamentoId;
	private Long parceiroLocalId;
	private Long caracteristicaMovimentoFinanceiroId;
	private Long carteiraFinanceiraId;
	private Long grupoFinanceiroId;
	private Long fatoGeradorId;
	private LocalDate dataMovimentoInicial;
	private LocalDate dataMovimentoFinal;
	private Long nossoNumero;
	private String documento;
}
