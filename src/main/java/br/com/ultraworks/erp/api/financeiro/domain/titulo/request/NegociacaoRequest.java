package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NegociacaoRequest {

	private Long tipoOperacaoFinanceiraId;
	private LocalDate dataNegociacao;
	private Long tipoTituloId;
	private Long parceiroLocalId;
	private Long departamentoId;
	private String observacao;
	private BigDecimal valorJurosNegociado;
	private BigDecimal valorDescontoNegociado;
	private List<SelecionadosNegociacaoRequest> selecionados;
	private List<NovasParcelasNegociacaoRequest> novasParcelas;
}
