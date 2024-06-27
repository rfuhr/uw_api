package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubstituicaoCarteiraRequest {

	private Long tipoOperacaoFinanceiraId;
	private LocalDate dataMovimento;
	private Long departamentoId;
	private Long carteiraFinanceiraDestinoId;
	private Long contaId;
	private List<SelecionadosSubstituicaoCarteiraRequest> selecionados;
}
