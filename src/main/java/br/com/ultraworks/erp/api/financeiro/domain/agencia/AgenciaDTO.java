package br.com.ultraworks.erp.api.financeiro.domain.agencia;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AgenciaDTO {

	private Long id;
	private Long bancoId;
	private String bancoNome;
	private int dv;
	private int codigo;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private String sigla;
}
