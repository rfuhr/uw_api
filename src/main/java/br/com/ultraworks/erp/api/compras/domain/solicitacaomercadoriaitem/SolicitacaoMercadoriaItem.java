package br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificado;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItemConverter;
import br.com.ultraworks.erp.api.compras.domain.urgenciasolicitacaomercadoria.UrgenciaSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.urgenciasolicitacaomercadoria.UrgenciaSolicitacaoMercadoriaConverter;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "solicitacao_mercadoria_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SolicitacaoMercadoriaItem extends UWEntityBase {

	@EqualsAndHashCode.Include
	@Id
	@SequenceGenerator(name = "solicitacaoMercadoriaItemSeq", sequenceName = "seq_solicitacao_mercadoria_item", allocationSize = 1)
	@GeneratedValue(generator = "solicitacaoMercadoriaItemSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "solicitacao_mercadoria_id")
	private SolicitacaoMercadoria solicitacaoMercadoria;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "item_simplificado_id")
	private ItemSimplificado itemSimplificado;

	@OneToOne
	@JoinColumn(name = "depto_entrega_id")
	private Departamento departamentoEntrega;

	@Column(name = "qtd_solicitada")
	private BigDecimal quantidadeSolicitada;

	@Column(name = "qtd_enviada")
	private BigDecimal quantidadeEnviada;

	@Column(name = "qtd_cancelada")
	private BigDecimal quantidadeCancelada;

	@Column(name = "observacao")
	private String observacao;

	@OneToOne
	@JoinColumn(name = "usuario_solicitacao_id")
	private Usuario usuarioSolicitacao;

	@Column(name = "data_solicitacao")
	private LocalDate dataSolicitacao;

	@OneToOne
	@JoinColumn(name = "usuario_atendente_id")
	private Usuario usuarioAtendente;

	@Column(name = "data_atendente")
	private LocalDate dataAtendente;

	@Column(name = "previsao_dias_utilizacao")
	private int previsaoDiasUtilizacao;

	@Convert(converter = UrgenciaSolicitacaoMercadoriaConverter.class)
	@Column(name = "urgencia_solicitacao_mercadoria")
	private UrgenciaSolicitacaoMercadoria urgenciaSolicitacaoMercadoria;

	@Convert(converter = StatusSolicitacaoMercadoriaItemConverter.class)
	@Column(name = "status")
	private StatusSolicitacaoMercadoriaItem status;
}
