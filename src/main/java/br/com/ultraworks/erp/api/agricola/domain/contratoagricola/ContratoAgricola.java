package br.com.ultraworks.erp.api.agricola.domain.contratoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDesconto;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcela;
import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "contrato_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@UniqueValidationGroup({
		@UniqueValidation(fields = { "numero" }, label = "Já existe contrato agrícola com este número") })
public class ContratoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "ContratoAgricolaSeq", sequenceName = "seq_contrato_agricola", allocationSize = 1)
	@GeneratedValue(generator = "ContratoAgricolaSeq")
	private Long id;

	private int numero;
	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;

	@OneToOne
	@JoinColumn(name = "avalista_id")
	private Parceiro avalista;

	@OneToOne
	@JoinColumn(name = "indice_financeiro_id")
	private IndiceFinanceiro indiceFinanceiro;
	
	@OneToOne
	@JoinColumn(name = "carteira_financeira_id")
	private CarteiraFinanceira carteiraFinanceira;

	@OneToOne
	@JoinColumn(name = "fato_gerador_id")
	private FatoGerador fatoGerador;
	
	@OneToOne
	@JoinColumn(name = "tipo_contrato_agricola_id")
	private TipoContratoAgricola tipoContratoAgricola;

	@OneToOne
	@JoinColumn(name = "tipo_preco_agricola_id")
	private TipoPrecoAgricola tipoPrecoAgricola;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "grupo_operacao_agricola_id")
	private GrupoOperacaoAgricola grupoOperacaoAgricola;

	@OneToOne
	@JoinColumn(name = "finalidade_contrato_agricola_id")
	private FinalidadeContratoAgricola finalidadeContratoAgricola;

	@OneToOne
	@JoinColumn(name = "predefinicao_preco_agricola_id")
	private PredefinicaoPrecoAgricola predefinicaoPrecoAgricola;

	@OneToOne
	@JoinColumn(name = "safra_id")
	private Safra safra;

	@Column(name = "qtd_contratada")
	private BigDecimal quantidadeContratada;

	@Column(name = "valor_unitario_liquido")
	private BigDecimal valorUnitarioLiquido;

	@Column(name = "valor_unitario_bruto")
	private BigDecimal valorUnitarioBruto;

	@Column(name = "valor_bruto")
	private BigDecimal valorBruto;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_liquido")
	private BigDecimal valorLiquido;

	@Column(name = "data_documento")
	private LocalDate dataDocumento;

	@Column(name = "data_movimento")
	private LocalDate dataMovimento;

	@Column(name = "qtd_baixada")
	private BigDecimal quantidadeBaixada;

	@Column(name = "valor_baixado")
	private BigDecimal valorBaixado;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "data_limite_entrega")
	private LocalDate dataLimiteEntrega;

	@OneToOne
	@JoinColumn(name = "regra_atividade_id")
	private RegraAtividade regraAtividade;

	@Column(name = "valor_unitario_base")
	private BigDecimal valorUnitarioBase;

	@Column(name = "valor_premio")
	private BigDecimal valorPremio;

	@Column(name = "qtd_acordo_premio")
	private BigDecimal quantidadeAcordoPremio;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "nivel_class1")
	private BigDecimal nivelClass1;

	@Column(name = "nivel_class2")
	private BigDecimal nivelClass2;

	@Column(name = "nivel_class3")
	private BigDecimal nivelClass3;

	@Column(name = "nivel_class4")
	private BigDecimal nivelClass4;

	@Transient
	private List<ContratoAgricolaParcela> parcelas;
	
	@Transient
	private List<ContratoAgricolaDesconto> descontos;
	
}
