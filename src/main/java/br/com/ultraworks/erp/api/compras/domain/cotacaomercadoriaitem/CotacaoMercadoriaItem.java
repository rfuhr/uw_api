package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificado;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
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
@Table(name = "cotacao_mercadoria_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CotacaoMercadoriaItem extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "cotacaoMercadoriaItemSeq", sequenceName = "seq_cotacao_mercadoria_item", allocationSize = 1)
	@GeneratedValue(generator = "cotacaoMercadoriaItemSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cotacao_mercadoria_parceiro_id")
	private CotacaoMercadoriaParceiro cotacaoMercadoriaParceiro;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToOne
	@JoinColumn(name = "item_simplificado_id")
	private ItemSimplificado itemSimplificado;
	
	@OneToOne
	@JoinColumn(name = "solicitacao_mercadoria_item_id")
	private SolicitacaoMercadoriaItem solicitacaoMercadoriaItem;

}
