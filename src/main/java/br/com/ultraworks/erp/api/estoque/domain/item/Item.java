package br.com.ultraworks.erp.api.estoque.domain.item;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.estoque.domain.linha.Linha;
import br.com.ultraworks.erp.api.estoque.domain.marca.Marca;
import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItem;
import br.com.ultraworks.erp.api.fiscal.domain.ncm.Ncm;
import br.com.ultraworks.erp.api.fiscal.domain.origem.Origem;
import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedida;
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
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Item extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "itemSeq", sequenceName = "seq_item", allocationSize = 1)
	@GeneratedValue(generator = "itemSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sku;
	private String descritivo;

	@OneToOne
	@JoinColumn(name = "unidade_medida_comercial_id")
	private UnidadeMedida unidadeMedidaComercial;

	@OneToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@OneToOne
	@JoinColumn(name = "linha_id")
	private Linha linha;

	@OneToOne
	@JoinColumn(name = "plano_classificacao_item_id")
	private PlanoClassificacaoItem planoClassificacaoItem;

	@Column(name = "gtinEan")
	private String gtinEan;

	@Column(name = "proprio")
	private boolean produtoProprio;

	@Column(name = "fracionado")
	private boolean fracionado;

	@Column(name = "controla_estoque")
	private boolean controlaEstoque;

	@OneToOne
	@JoinColumn(name = "unidade_medida_estoque_id")
	private UnidadeMedida unidadeMedidaEstoque;

	@Column(name = "quantidade_minima_estoque")
	private BigDecimal quantidadeMinimaEstoque;

	@Column(name = "quantidade_maxima_estoque")
	private BigDecimal quantidadeMaximaEstoque;

	@Column(name = "quantidade_ideal_estoque")
	private BigDecimal quantidadeIdealEstoque;

	@Column(name = "quantidade_alerta_estoque")
	private BigDecimal quantidadeAlertaEstoque;

	@OneToOne
	@JoinColumn(name = "origem_id")
	private Origem origem;

	@OneToOne
	@JoinColumn(name = "ncm_id")
	private Ncm ncm;

	@OneToOne
	@JoinColumn(name = "unidade_medida_tributavel_id")
	private UnidadeMedida unidadeMedidaTributavel;
}
