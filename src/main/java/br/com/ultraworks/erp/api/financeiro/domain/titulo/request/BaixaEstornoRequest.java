package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaixaEstornoRequest {

	private LocalDate dataMovimento;
	private Long departamentoId;
	private Long motivoEstornoFinanceiroId;
	private String observacao;
	private List<SelecionadosEstornoBaixaRequest> baixasSelecionadas;
}
