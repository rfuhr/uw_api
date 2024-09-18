package br.com.ultraworks.erp.api.agricola.domain.pesagem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.SituacaoPesagem;
import br.com.ultraworks.erp.api.agricola.domain.situacaopesagem.SituacaoPesagemConverter;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
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
@Table(name = "pesagem")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Pesagem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "pesagemSeq", sequenceName = "seq_pesagem", allocationSize = 1)
	@GeneratedValue(generator = "pesagemSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

	@Column(name = "data_pesagem")
	private LocalDate dataPesagem;

	@OneToOne
	@JoinColumn(name = "operacao_interna_id")
	private OperacaoInterna operacaoInterna;

	@Column(name = "tem_cadastro")
	private boolean temCadastro;

	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;

	@OneToOne
	@JoinColumn(name = "parceiro_local_propriedade_id")
	private ParceiroLocalPropriedade parceiroLocalPropriedade;

	@Column(name = "nome_parceiro")
	private String nomeParceiro;

	@Column(name = "nome_propriedade")
	private String nomePropriedade;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "safra_id")
	private Safra safra;

	@Column(name = "peso_entrada")
	private BigDecimal pesoEntrada;

	@Column(name = "peso_saida")
	private BigDecimal pesoSaida;

	@Column(name = "peso_bruto")
	private BigDecimal pesoBruto;

	@Column(name = "descontos")
	private BigDecimal descontos;

	@Column(name = "peso_liquido")
	private BigDecimal pesoLiquido;

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

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "peso_entrada_manual")
	private boolean pesoEntradaManual;

	@Column(name = "justificativa_peso_entrada")
	private String justificaticaPesoEntrada;

	@Column(name = "peso_saida_manual")
	private boolean pesoSaidaManual;

	@Column(name = "justificativa_peso_saida")
	private String justificaticaPesoSaida;

	@Convert(converter = SituacaoPesagemConverter.class)
	@Column(name = "situacao")
	private SituacaoPesagem situacao;

	@Transient
	private List<PesagemClassificacao> classificacoes;
}
