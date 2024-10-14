package br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.validator.ValidaBaseCalculoAgricola;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoCalculoAgricolaDTO {

	private Long id;
	@NotNull
	private Long codigo;
	
	@ValidaBaseCalculoAgricola
	private String baseCalculoAgricola;
	
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;
	
	private boolean baseAuxilialDesconto;
	private String indicadorDC;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
	
	private String baseCalculoAgricolaNome;
}
