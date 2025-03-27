package br.com.ultraworks.erp.api.estoque.domain.movimentoestoque;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoqueConverter;
import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoqueConverter;
import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoqueConverter;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimento_estoque")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class MovimentoEstoque extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "movimentoEstoqueSeq", sequenceName = "seq_movimento_estoque", allocationSize = 1)
	@GeneratedValue(generator = "movimentoEstoqueSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "empresa_filial_id")
	@JsonBackReference
	private EmpresaFilial empresaFilial;
	
	@ManyToOne
	@JoinColumn(name = "local_estoque_id")
	@JsonBackReference
	private LocalEstoque localEstoque;
	
	@ManyToOne
	@JoinColumn(name = "grupo_contabil_id")
	@JsonBackReference
	private GrupoContabil grupoContabil;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonBackReference
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;
	
	@Convert(converter = TipoMovimentoEstoqueConverter.class)
	@Column(name = "tipo_movimento_estoque")
	private TipoMovimentoEstoque tipoMovimentoEstoque;
	
	@Convert(converter = OperacaoEstoqueConverter.class)
	@Column(name = "operacao_estoque_fisico")
	private OperacaoEstoque operacaoEstoqueFisico;

	@Convert(converter = OperacaoEstoqueConverter.class)
	@Column(name = "operacao_estoque_financeiro")
	private OperacaoEstoque operacaoEstoqueFinanceiro;
	
	@Column(name = "calcula_custo_medio_saldo")
	private boolean calculaCustoMedioSaldo;
	
	private LocalDate data;
	
	private String documento;
	
	private boolean entrada;
	
	private BigDecimal quantidade;

	@Column(name = "custo_medio")
	private BigDecimal custoMedio;

	private BigDecimal valor;
	
	@Convert(converter = TipoDocumentoEstoqueConverter.class)
	@Column(name = "tipo_documento_estoque")
	private TipoDocumentoEstoque tipoDocumentoEstoque;
	
	@Column(name = "protocolo_documento")
	private Long protocoloDocumento;
	
	@Column(name = "debito_credito")
	private String debitoCredito;
	
}
