package br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacao_financeira")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoFinanceira extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "operacaoFinanceiraSeq", sequenceName = "seq_operacao_financeira", allocationSize = 1)
	@GeneratedValue(generator = "operacaoFinanceiraSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "tipo_operacao_financeira_id")
	private TipoOperacaoFinanceira tipoOperacaoFinanceira;

	@OneToOne
	@JoinColumn(name = "operacao_movimento_financeiro_id")
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro;

	@OneToOne
	@JoinColumn(name = "operacao_acessoria_financeira_id")
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira;
	
	@Column(name = "idn_gera_ficha")
	private boolean geraFichaFinanceira;
	
	@Column(name = "idn_integra_bco")
	private boolean integraBanco;
	
	@Column(name = "operacao_banco_id")
	private long operacaoBancaria;
	
	@Column(name = "idn_gera_apurfin")
	private boolean geraApuracaoFinanceira;
}
