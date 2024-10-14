package br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.validator.ValidaIdentificacaoDocumentoAgricola;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdemCalculoAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@ValidaIdentificacaoDocumentoAgricola
	private String identificacaoDocumentoAgricola;
	@NotNull
	private Long tipoCalculoAgricolaId;
	@NotNull
	private int ordem;

	private String itemNome;
	private String identificacaoDocumentoAgricolaNome;
	private String tipoCalculoAgricolaNome;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
