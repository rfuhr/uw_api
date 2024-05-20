package br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoqueConverter;
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
@Table(name = "operacao_interna_estoque")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaEstoque extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaEstoqueSeq", sequenceName = "seq_operacao_interna_estoque", allocationSize = 1)
	@GeneratedValue(generator = "opInternaEstoqueSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;
	
	@Column(name = "informa_local_estoque")
	private boolean informaLocalEstoque;
	
	@Column(name = "informa_grupo_contabil")
	private boolean informaGrupoContabil;

	@Column(name = "calcula_custo_medio")
	private boolean calculaCustoMedio;

	@ManyToOne
	@JoinColumn(name = "local_estoque_id")
	@JsonBackReference
	private LocalEstoque localEstoque;
	
	@ManyToOne
	@JoinColumn(name = "grupo_contabil_id")
	@JsonBackReference
	private GrupoContabil grupoContabil;
	
	@Convert(converter = OperacaoEstoqueConverter.class)
	@Column(name = "operacao_estoque_fisico")
	private OperacaoEstoque operacaoEstoqueFisico;

	@Convert(converter = OperacaoEstoqueConverter.class)
	@Column(name = "operacao_estoque_financeiro")
	private OperacaoEstoque operacaoEstoqueFinanceiro;
	
}
