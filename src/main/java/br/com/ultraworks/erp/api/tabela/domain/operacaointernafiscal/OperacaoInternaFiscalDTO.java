package br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal;

import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacaoDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OperacaoInternaFiscalDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	private String nome;
	@NotNull
	private String sigla;	
	private NaturezaOperacaoDTO naturezaOperacao;
	private Long naturezaOperacaoId;
}
