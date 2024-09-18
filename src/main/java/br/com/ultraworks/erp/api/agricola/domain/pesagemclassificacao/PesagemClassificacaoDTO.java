package br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PesagemClassificacaoDTO {

	private Long id;
	@NotNull
	private Long pesagemId;
	@NotNull
	private Long tipoClassificacaoAgricolaId;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private BigDecimal percentualDesconto;
	@NotNull
	private BigDecimal desconto;

	private int tipoClassificacaoAgricolaCodigo;
	private String tipoClassificacaoAgricolaNome;
}
