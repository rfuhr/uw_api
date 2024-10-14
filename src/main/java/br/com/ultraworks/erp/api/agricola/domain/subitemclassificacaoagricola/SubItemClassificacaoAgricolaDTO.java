package br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SubItemClassificacaoAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	
	@NotNull
	private Long itemClassificacaoAgricolaId;
	
	private Long itemClassificacaoAgricolaGeradoId;
	private Long subItemClassificacaoAgricolaGeradoId;

	@NotNull
	private String codigo;

	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;
	
	private BigDecimal indiceReferencia;

	private Long tipoCalculoAgricolaIdRomaneio;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;

	private String itemNome;
	private String itemClassificacaoAgricolaNome;
	private String itemClassificacaoAgricolaGeradoNome;
	private String subItemClassificacaoAgricolaGeradoNome;
}
