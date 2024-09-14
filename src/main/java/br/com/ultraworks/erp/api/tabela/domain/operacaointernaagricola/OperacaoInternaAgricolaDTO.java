package br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.validator.ValidaOperacaoEstoque;
import lombok.Data;

@Data
public class OperacaoInternaAgricolaDTO {

	private Long id;
	private Long operacaoInternaId;
	private boolean selecionaPesagem;
}
