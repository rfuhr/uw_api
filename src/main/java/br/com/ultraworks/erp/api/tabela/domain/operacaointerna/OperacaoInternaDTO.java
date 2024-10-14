package br.com.ultraworks.erp.api.tabela.domain.operacaointerna;

import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoqueDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiroDTO;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscalDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OperacaoInternaDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	private String nome;
	@NotNull
	private String sigla;
	private NaturezaOperacaoDTO naturezaOperacao;
	private Long naturezaOperacaoId;
	private boolean caracteristicaFiscal;
	private boolean caracteristicaEstoque;
	private boolean caracteristicaAgricola;
	private boolean caracteristicaFinanceira;
	private OperacaoInternaFiscalDTO operacaoInternaFiscal;
	private OperacaoInternaEstoqueDTO operacaoInternaEstoque;
	private OperacaoInternaAgricolaDTO operacaoInternaAgricola;
	private OperacaoInternaFinanceiroDTO operacaoInternaFinanceiro;
}
