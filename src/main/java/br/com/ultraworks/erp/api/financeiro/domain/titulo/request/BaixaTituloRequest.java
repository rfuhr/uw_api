package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaixaTituloRequest {

	private LocalDate dataMovimento;
	private Long departamentoId;
	private Long tipoOperacaoFinanceiraId;
	private String observacao;
	private List<SelecionadosBaixaTituloRequest> selecionados;
}
