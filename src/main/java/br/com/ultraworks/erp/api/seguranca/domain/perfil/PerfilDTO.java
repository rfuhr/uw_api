package br.com.ultraworks.erp.api.seguranca.domain.perfil;

import java.util.List;

import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidadeDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PerfilDTO {

	private Long id;

	@NotEmpty
	@Size(min = 5, max = 60)
	private String nome;
	@NotEmpty
	@Size(min = 5, max = 255)
	private String descricao;
	
	private List<PerfilFuncionalidadeDTO> perfisFuncionalidades;
	
}
