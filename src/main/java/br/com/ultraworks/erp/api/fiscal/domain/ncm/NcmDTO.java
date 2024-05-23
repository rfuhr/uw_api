package br.com.ultraworks.erp.api.fiscal.domain.ncm;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico.validator.ValidaTipoSinteticoAnalitico;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NcmDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 4000)
	@FriendlyName("Nome")
	private String nome;
	private String codigo;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	@ValidaTipoSinteticoAnalitico
	private String tipoSinteticoAnalitico;
	
	private String tipoSinteticoAnaliticoName;

}
