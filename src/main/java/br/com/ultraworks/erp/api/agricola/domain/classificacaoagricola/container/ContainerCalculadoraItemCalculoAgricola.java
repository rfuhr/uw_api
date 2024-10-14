package br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.container;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContainerCalculadoraItemCalculoAgricola {

	private Long itemClassificacaoAgricolaId;
	private Long subItemClassificacaoAgricolaId;
	private Long grupoClassificacaoAgricolaId;
	private String baseCalculoAgricola;
	private BigDecimal valorBaseCalculo;
	private BigDecimal valorBaseCalculoComplementar;
	private BigDecimal valor;
	private String indicadorDC;
	private int ordem;
	private BigDecimal fatorCalculo;
	private String tipoTaxaAgricola;
	private boolean gerado;
	
	private String itemClassificacaoAgricolaNome;
	private String grupoClassificacaoAgricolaNome;
}
