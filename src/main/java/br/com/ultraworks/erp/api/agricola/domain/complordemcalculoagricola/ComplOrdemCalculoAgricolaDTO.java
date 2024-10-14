package br.com.ultraworks.erp.api.agricola.domain.complordemcalculoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.validator.ValidaIdentificacaoDocumentoAgricola;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComplOrdemCalculoAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@ValidaIdentificacaoDocumentoAgricola
	private String identificacaoDocumentoAgricola;
	@NotNull
	private Long tipoCalculoAgricolaId;
	@NotNull
	private int ordem;
	@NotNull
	private int ordemCompl;

	private String itemNome;
	private String identificacaoDocumentoAgricolaNome;
	private String tipoCalculoAgricolaNome;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
