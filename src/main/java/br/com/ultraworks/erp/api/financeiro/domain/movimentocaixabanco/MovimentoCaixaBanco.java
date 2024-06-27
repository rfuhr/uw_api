package br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimento_cxbco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class MovimentoCaixaBanco extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "movimentoCaixaBancoSeq", sequenceName = "seq_movimento_cxbco", allocationSize = 1)
	@GeneratedValue(generator = "movimentoCaixaBancoSeq")
	private Long id;

	@Column(name = "nosso_numero")
	private Long nossoNumero;

	@OneToOne
	@JoinColumn(name = "operacao_cxbco_id")
	private OperacaoCaixaBanco operacaoCaixaBanco;

	@OneToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@OneToOne
	@JoinColumn(name = "conta_id_transf")
	private Conta contaTransf;	
	
	@Column(name = "data_movimento")
	private LocalDate dataMovimento;
	
	@Column(name = "numero_documento")
	private String numeroDocumento;		
	
	@Column(name = "data_movimento_bco")
	private LocalDate dataMovimentoBanco;
	
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	
	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;	
	
	@OneToOne
	@JoinColumn(name = "fato_gerador_id")
	private FatoGerador fatoGerador;
	
	@OneToOne
	@JoinColumn(name = "historico_padrao_id")
	private HistoricoPadrao historicoPadrao;	
	
	@Column(name = "complemento_hp")
	private String complementoHP;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Convert(converter = IndicadorDCConverter.class)
	@Column(name = "indicador_dc")
	private IndicadorDC indicadorDC;
	
	@Column(name = "idn_compensado")
	private boolean compensado;
	
	@Column(name = "data_compensado")
	private LocalDate dataCompensado;
	
	@OneToOne
	@JoinColumn(name = "grupo_financeiro_id")
	private GrupoFinanceiro grupoFinanceiro;
	
	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
}
