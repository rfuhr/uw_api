package br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDCConverter;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "parametro_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ParametroFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parametroFinanceiroSeq", sequenceName = "seq_parametro_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "parametroFinanceiroSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "tipo_titulo_id")
	private TipoTitulo tipoTitulo;

	@OneToOne
	@JoinColumn(name = "caracteristica_movimento_financeiro_id")
	private CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro;

	@OneToOne
	@JoinColumn(name = "carteira_financeira_id")
	private CarteiraFinanceira carteiraFinanceira;

	@OneToOne
	@JoinColumn(name = "fato_gerador_id")
	private FatoGerador fatoGerador;

	@OneToOne
	@JoinColumn(name = "operacao_movimento_financeiro_id")
	private OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro;

	@OneToOne
	@JoinColumn(name = "operacao_acessoria_financeira_id")
	private OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira;

	@Convert(converter = IndicadorDCConverter.class)
	@Column(name = "indicador_dc")
	private IndicadorDC indicadorDC;

	@OneToOne
	@JoinColumn(name = "plano_classificacao_financeira_id")
	private PlanoClassificacaoFinanceira planoClassificacaoFinanceira;

}
