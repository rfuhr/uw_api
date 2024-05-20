package br.com.ultraworks.erp.api.tabela.domain.operacaointerna;

import java.util.List;

import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacao;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_interna")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInterna extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaSeq", sequenceName = "seq_operacao_interna", allocationSize = 1)
	@GeneratedValue(generator = "opInternaSeq")
	private Long id;

	private String nome;
	private String sigla;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "natureza_operacao_id")
	private NaturezaOperacao naturezaOperacao;
	
	@Column(name = "idn_fiscal")
	private boolean caracteristicaFiscal;
	
	@Column(name = "caracteristica_estoque")
	private boolean caracteristicaEstoque;
	
	@Transient
	private List<OperacaoInternaFiscal> operacoesInternasFiscal;
	
	@Transient
	private OperacaoInternaEstoque operacaoInternaEstoque;
}
