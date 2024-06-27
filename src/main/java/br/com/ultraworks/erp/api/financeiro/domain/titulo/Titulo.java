package br.com.ultraworks.erp.api.financeiro.domain.titulo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "titulo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Titulo extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "tituloSeq", sequenceName = "seq_titulo", allocationSize = 1)
	@GeneratedValue(generator = "tituloSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;
	
	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;

	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;

	@OneToOne
	@JoinColumn(name = "tipo_titulo_id")
	private TipoTitulo tipoTitulo;

	@OneToOne
	@JoinColumn(name = "grupo_financeiro_id")
	private GrupoFinanceiro grupoFinanceiro;

	@OneToOne
	@JoinColumn(name = "fato_gerador_id")
	private FatoGerador fatoGerador;

	@OneToOne
	@JoinColumn(name = "caracteristica_movimento_financeiro_id")
	private CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro;

	@OneToOne
	@JoinColumn(name = "historico_padrao_id")
	private HistoricoPadrao historicoPadrao;

	private String documento;

	@Column(name = "data_documento")
	private LocalDate dataDocumento;

	private String observacao;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	private String historico;
	@Column(name = "nosso_numero")
	private Long nossoNumero;
	
	@OneToOne
	@JoinColumn(name = "negociacao_financeira_id")
	private NegociacaoFinanceira negociacaoFinanceira;
	
	@Transient
	private List<ParcelaFinanceiro> parcelas;
}
