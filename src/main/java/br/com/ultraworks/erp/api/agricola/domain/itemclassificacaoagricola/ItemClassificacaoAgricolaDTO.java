package br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemClassificacaoAgricolaDTO {

	private Long id;

	@NotNull
	private Long grupoClassificacaoAgricolaId;

	@NotNull
	private Long codigo;

	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;

	private Long tipoCalculoAgricolaIdRomaneio;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;

	private String grupoClassificacaoAgricolaNome;
	private String tipoCalculoAgricolaNomeRomaneio;
}
