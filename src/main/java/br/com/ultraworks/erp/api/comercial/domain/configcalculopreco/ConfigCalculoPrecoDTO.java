package br.com.ultraworks.erp.api.comercial.domain.configcalculopreco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInternaDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfigCalculoPrecoDTO {

	private Long id;
	private Long tipoPrecoId;
	private String tipoPrecoNome;
	private int tipoPrecoCodigo;
	private Long operacaoInternaId;
	private String operacaoInternaNome;
	private String operacaoInternaSigla;
	private boolean aplicaIndicesMarkup;
	private boolean aplicaPercentualFixo;
	private int diasBuscaPrecos;
	private BigDecimal percentual;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@Valid
	private List<ConfigCalculoPrecoOperInternaDTO> configCalculoPrecoOperInternas;
}
