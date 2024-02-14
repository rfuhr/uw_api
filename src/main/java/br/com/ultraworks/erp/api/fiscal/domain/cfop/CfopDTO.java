package br.com.ultraworks.erp.api.fiscal.domain.cfop;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.tipooperacao.validator.ValidaTipoOperacao;
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
	
	@ValidaTipoOperacao
	private String tipoOperacao;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;

}
