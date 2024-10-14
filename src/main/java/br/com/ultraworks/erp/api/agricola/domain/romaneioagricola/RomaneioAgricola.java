package br.com.ultraworks.erp.api.agricola.domain.romaneioagricola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.balanca.Balanca;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneioConverter;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNota;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacao;
import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneioConverter;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "romaneio_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class RomaneioAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "romaneioAgricolaSeq", sequenceName = "seq_romaneio_agricola", allocationSize = 1)
	@GeneratedValue(generator = "romaneioAgricolaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@Convert(converter = OrigemRomaneioConverter.class)
	@Column(name = "origem")
	private OrigemRomaneio origem;

	@Column(name = "numero")
	private Long numero;

	@Column(name = "data_documento")
	private LocalDate dataDocumento;

	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;

	@OneToOne
	@JoinColumn(name = "parceiro_local_propriedade_id")
	private ParceiroLocalPropriedade parceiroLocalPropriedade;
	
	@OneToOne
	@JoinColumn(name = "regra_atividade_id")
	private RegraAtividade regraAtividade;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "safra_id")
	private Safra safra;

	@OneToOne
	@JoinColumn(name = "grupo_operacao_agricola_id")
	private GrupoOperacaoAgricola grupoOperacaoAgricola;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@OneToOne
	@JoinColumn(name = "origem_producao_agricola_id")
	private OrigemProducaoAgricola origemProducaoAgricola;
	
	@OneToOne
	@JoinColumn(name = "balanca_id")
	private Balanca balanca;
	
	@Column(name = "peso_entrada")
	private BigDecimal pesoEntrada;

	@Column(name = "peso_saida")
	private BigDecimal pesoSaida;

	@Column(name = "peso_bruto")
	private BigDecimal pesoLiquido;

	@Column(name = "descontos_acrescimo")
	private BigDecimal descontosAcrescimo;

	@Column(name = "peso_liquido")
	private BigDecimal pesoFinal;

	@Column(name = "peso_entrada_manual")
	private boolean pesoEntradaManual;

	@Column(name = "justificativa_peso_entrada")
	private String justificaticaPesoEntrada;

	@Column(name = "peso_saida_manual")
	private boolean pesoSaidaManual;

	@Column(name = "justificativa_peso_saida")
	private String justificaticaPesoSaida;

	@Column(name = "placa_1")
	private String placa1;

	@Column(name = "placa_2")
	private String placa2;

	@Column(name = "placa_3")
	private String placa3;

	@Column(name = "nome_motorista")
	private String nomeMotorista;

	@Column(name = "contato_motorista")
	private String contatoMotorista;

	@Column(name = "entrada")
	private boolean entrada;
	
	@Column(name = "fixar_automatico")
	private boolean fixarAutomatico;

	@Column(name = "observacao")
	private String observacao;

	@Convert(converter = SituacaoRomaneioConverter.class)
	@Column(name = "situacao")
	private SituacaoRomaneio situacao;
	
	@OneToOne
	@JoinColumn(name = "contrato_agricola_id")
	private ContratoAgricola contratoAgricola;
	
	@OneToOne
	@JoinColumn(name = "tipo_preco_agricola_id")
	private TipoPrecoAgricola tipoPrecoAgricola;
	
	@OneToOne
	@JoinColumn(name = "nivel1_pred_preco")
	private SubItemClassificacaoAgricola nivel1PredPreco;

	@OneToOne
	@JoinColumn(name = "nivel2_pred_preco")
	private SubItemClassificacaoAgricola nivel2PredPreco;

	@OneToOne
	@JoinColumn(name = "nivel3_pred_preco")
	private SubItemClassificacaoAgricola nivel3PredPreco;

	@OneToOne
	@JoinColumn(name = "nivel4_pred_preco")
	private SubItemClassificacaoAgricola nivel4PredPreco;
	
	@Column(name = "preco_deposito")
	private BigDecimal precoDeposito;
	
	@Column(name = "peso_final_faturar")
	private BigDecimal pesoFinalFaturar;
	
	@Column(name = "saldo_fixar")
	private BigDecimal saldoFixar;
	
	@Column(name = "controle")
	private String controle;
	
	@Column(name = "controle_dv")
	private int controleDv;
	
	@Column(name = "numero_nf_deposito")
	private int numeroNfDeposito;
	
	@Transient
	private List<RomaneioAgricolaClassificacao> classificacoes;

	@Transient
	private List<RomaneioAgricolaNota> notas;
	
	@Transient
	private List<RomaneioAgricolaParcelaFixacao> parcelas;
}
