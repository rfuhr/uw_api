package br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
@Table(name = "operacao_interna_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OperacaoInternaFinanceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "opInternaFinanceiroSeq", sequenceName = "seq_operacao_interna_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "opInternaFinanceiroSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "operacao_interna_id")
	@JsonBackReference
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "indice_financeiro_padrao_id")
	private IndiceFinanceiro indiceFinanceiroPadrao;
	
	@OneToOne
	@JoinColumn(name = "tipo_titulo_id")
	private TipoTitulo tipoTitulo;
	
	@OneToOne
	@JoinColumn(name = "grupo_financeiro_id")
	private GrupoFinanceiro grupoFinanceiro;
	
	@OneToOne
	@JoinColumn(name = "caracteristica_movimento_financeiro_id")
	private CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro;
	
	@OneToOne
	@JoinColumn(name = "historico_padrao_id")
	private HistoricoPadrao historicoPadrao;
	
	@OneToOne
	@JoinColumn(name = "carteira_financeira_id")
	private CarteiraFinanceira carteiraFinanceira;
	
	@OneToOne
	@JoinColumn(name = "fato_gerador_id")
	private FatoGerador fatoGerador;
	
	@OneToOne
	@JoinColumn(name = "motivo_estorno_financeiro_id")
	private MotivoEstornoFinanceiro motivoEstornoFinanceiro;
}
