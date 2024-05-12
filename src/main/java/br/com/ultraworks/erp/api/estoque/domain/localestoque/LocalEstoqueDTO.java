package br.com.ultraworks.erp.api.estoque.domain.localestoque;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LocalEstoqueDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private int codigo;
	private boolean filialEspecifica;
	private Long tipoLocalEstoqueId;
	private int tipoLocalEstoqueCodigo;
	private String tipoLocalEstoqueNome;
	
	private Long empresaFilialId;
	private String empresaFilialSigla;
	private String empresaFilialNome;
	
}
