package br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlanoClassificacaoItemDTO {

	private Long id;

	private Long contaSuperiorId;
	private String contaSuperiorCodigo;
	private String contaSuperiorNome;
	
	@NotNull
	@NotEmpty
	@Size(max = 30)
	@FriendlyName("Código")
	private String codigo;
	
	@NotNull
	@NotEmpty
	@Size(max = 120)
	@FriendlyName("Nome")
	private String nome;

	@NotNull
	@FriendlyName("Nível")
	private int nivel;
	
	@NotNull
	@FriendlyName("Sintética")
	private boolean sintetica;	
	
}
