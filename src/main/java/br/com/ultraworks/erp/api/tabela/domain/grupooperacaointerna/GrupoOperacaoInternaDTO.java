package br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna;

import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacaoDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GrupoOperacaoInternaDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	private String nome;
	@NotNull
	private String sigla;	
	private TipoOperacaoDTO tipoOperacao;

}
