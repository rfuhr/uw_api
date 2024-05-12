package br.com.ultraworks.erp.api.estoque.domain.movimentoestoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.validator.ValidaOperacaoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.validator.ValidaTipoDocumentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.validator.ValidaTipoMovimentoEstoque;
import lombok.Data;

@Data
public class MovimentoEstoqueDTO {

	private Long id;
	private Long empresaFilialId;
	private String empresaFilialSigla;
	private String empresaFilialNome;
	private Long grupoContabilId;
	private int grupoContabilCodigo;
	private String grupoContabilNome;
	private Long localEstoqueId;
	private int localEstoqueCodigo;
	private String localEstoqueNome;
	private Long itemId;
	private int itemCodigo;
	private String itemNome;
	private Long operacaoInternaId;
	private String operacaoInternaSigla;
	private String operacaoInternaNome;
	
	private boolean calculaCustoMedioSaldo;
	private LocalDate data;
	private String documento;
	private boolean entrada;
	private BigDecimal quantidade;
	private BigDecimal valor;
	private BigDecimal custoMedio;
	
	@ValidaTipoMovimentoEstoque
	private String tipoMovimentoEstoque;
	@ValidaOperacaoEstoque
	private String operacaoEstoqueFisico;
	@ValidaOperacaoEstoque
	private String operacaoEstoqueFinanceiro;
	@ValidaTipoDocumentoEstoque
	private String tipoDocumentoEstoque;
	
	private Long protocoloDocumento;
}
