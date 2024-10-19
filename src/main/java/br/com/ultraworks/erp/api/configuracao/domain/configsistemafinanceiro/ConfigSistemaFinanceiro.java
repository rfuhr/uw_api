package br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_sistema_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigSistemaFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "configSistemaFinanceiroSeq", sequenceName = "seq_config_sistema_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "configSistemaFinanceiroSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "config_sistema_id")
	private ConfigSistema configSistema;
	
	@OneToOne
	@JoinColumn(name = "tipo_titulo_receber")
	private TipoTitulo tipoTituloReceber;
	
	@OneToOne
	@JoinColumn(name = "tipo_titulo_pagar")
	private TipoTitulo tipoTituloPagar;

	@OneToOne
	@JoinColumn(name = "oper_movfin_inc")
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiroInclusao;

	@OneToOne
	@JoinColumn(name = "oper_movfin_bx")
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiroBaixa;

	@OneToOne
	@JoinColumn(name = "oper_acefin_princ")
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceiraPrincipal;

	@OneToOne
	@JoinColumn(name = "oper_acefin_jurpac")
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceiraJurosPactuado;

	@OneToOne
	@JoinColumn(name = "tipo_opfin_lcto")
	private TipoOperacaoFinanceira tipoOperacaoFinanceiraLancamento;

	@OneToOne
	@JoinColumn(name = "tipo_opfin_estorno")
	private TipoOperacaoFinanceira tipoOperacaoFinanceiraEstorno;

	@OneToOne
	@JoinColumn(name = "grupo_financeiro_neg")
	private GrupoFinanceiro grupoFinanceiroNegociacao;

	@OneToOne
	@JoinColumn(name = "fato_gerador_neg")
	private FatoGerador fatoGeradorNegociacao;

	@OneToOne
	@JoinColumn(name = "carteira_financeira_neg")
	private CarteiraFinanceira carteiraFinanceiraNegociacao;

	@OneToOne
	@JoinColumn(name = "caracter_movfin_neg")
	private CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiroNegociacao;

	@OneToOne
	@JoinColumn(name = "hist_padrao_neg")
	private HistoricoPadrao historicoPadraoNegociacao;
}
