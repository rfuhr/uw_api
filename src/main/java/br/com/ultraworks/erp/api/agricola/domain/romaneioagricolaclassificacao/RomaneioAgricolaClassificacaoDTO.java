package br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RomaneioAgricolaClassificacaoDTO {

	private Long id;
	@NotNull
	private Long romaneioAgricolaId;
	@NotNull
	private Long itemClassificacaoAgricolaId;
	private Long subItemClassificacaoAgricolaId;
	private String baseCalculoAgricola;
	private BigDecimal valorBaseCalculo;
	private BigDecimal valorBaseCalculoComplementar;
	private BigDecimal valor;
	private String indicadorDC;
	private int ordem;
	private BigDecimal fatorCalculo;
	private String tipoTaxaAgricola;
	private boolean gerado;
	private Long subItemClassificacaoAgricolaOrigemId;

	private String itemClassificacaoAgricolaNome;
	private String subItemClassificacaoAgricolaNome;
	private String baseCalculoAgricolaNome;
	private String tipoTaxaAgricolaNome;

	private Long grupoClassificacaoAgricolaId;
	private String grupoClassificacaoAgricolaNome;
	private String subItemClassificacaoAgricolaOrigemNome;
}
