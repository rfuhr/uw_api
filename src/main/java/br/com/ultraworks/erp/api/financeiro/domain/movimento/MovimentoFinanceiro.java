package br.com.ultraworks.erp.api.financeiro.domain.movimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.documentobaixafinanceiro.DocumentoBaixaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimento_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class MovimentoFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "movimentoSeq", sequenceName = "seq_movimento_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "movimentoSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "parcela_id")
	private ParcelaFinanceiro parcela;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@Column(name = "seq_mvto")
	private int seqMvto;

	@Column(name = "sub_seq_mvto")
	private int subSeqMvto;

	@OneToOne
	@JoinColumn(name = "tipo_operacao_financeira_id")
	private TipoOperacaoFinanceira tipoOperacaoFinanceira;

	@OneToOne
	@JoinColumn(name = "operacao_movimento_financeiro_id")
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro;

	@OneToOne
	@JoinColumn(name = "operacao_acessoria_financeira_id")
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira;

	@OneToOne
	@JoinColumn(name = "grupo_financeiro_id")
	private GrupoFinanceiro grupoFinanceiro;

	@OneToOne
	@JoinColumn(name = "carteira_financeira_id")
	private CarteiraFinanceira carteiraFinanceira;

	@Column(name = "valor_movimento")
	private BigDecimal valorMovimento;

	@Column(name = "data_movimento")
	private LocalDate dataMovimento;

	@Column(name = "saldo_parcela")
	private BigDecimal saldoParcela;

	@Column(name = "data_lancamento")
	private LocalDateTime dataLancamento;

	private String observacao;

	@OneToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;

	@OneToOne
	@JoinColumn(name = "documento_baixa_financeiro_id")
	private DocumentoBaixaFinanceiro documentoBaixaFinanceiro;
	
	@OneToOne
	@JoinColumn(name = "negociacao_financeira_id")
	private NegociacaoFinanceira negociacaoFinanceira;
}
