package br.com.ultraworks.erp.api.tabela.domain.regraatividade;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegraAtividadeDTO {

	private Long id;
	private Long codigo;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
}
