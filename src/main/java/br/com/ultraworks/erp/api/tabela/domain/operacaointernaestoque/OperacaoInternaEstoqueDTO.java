package br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.validator.ValidaOperacaoEstoque;
import lombok.Data;

@Data
public class OperacaoInternaEstoqueDTO {

	private Long id;
	private Long operacaoInternaId;
	private boolean informaLocalEstoque;
	private boolean informaGrupoContabil;
	private boolean calculaCustoMedio;
	private Long localEstoqueId;
	private int localEstoqueCodigo;
	private String localEstoqueNome;
	private Long grupoContabilId;
	private int grupoContabilCodigo;
	private String grupoContabilNome;
	@ValidaOperacaoEstoque
	private String operacaoEstoqueFisico;
	@ValidaOperacaoEstoque
	private String operacaoEstoqueFinanceiro;
}
