package br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.validator.ValidaOperacaoEstoque;
import lombok.Data;

@Data
public class ConfigCalculoPrecoOperInternaDTO {

	private Long id;
	private Long configCalculoPrecoId;
	private Long operacaoInternaId;
	private String operacaoInternaNome;
	private String operacaoInternaSigla;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@ValidaOperacaoEstoque
	private String operacaoEstoque;
	private String operacaoEstoqueName;
}
