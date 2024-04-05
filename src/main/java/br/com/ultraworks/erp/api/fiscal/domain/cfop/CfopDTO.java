package br.com.ultraworks.erp.api.fiscal.domain.cfop;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.validator.ValidaDestinoOperacao;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CfopDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 2000)
	@FriendlyName("Nome")
	private String nome;
	private int codigo;
	
	@ValidaDestinoOperacao
	private String destinoOperacao;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
