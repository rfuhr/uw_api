package br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro;

import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.SituacaoCotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.SituacaoCotacaoMercadoriaParceiroConverter;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cotacao_mercadoria_parceiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CotacaoMercadoriaParceiro extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "cotacaoMercadoriaParceiroSeq", sequenceName = "seq_cotacao_mercadoria_parceiro", allocationSize = 1)
	@GeneratedValue(generator = "cotacaoMercadoriaParceiroSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cotacao_mercadoria_id")
	private CotacaoMercadoria cotacaoMercadoria;

	@OneToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	@Convert(converter = SituacaoCotacaoMercadoriaParceiroConverter.class)
	@Column(name = "situacao")
	private SituacaoCotacaoMercadoriaParceiro situacao;

	@Transient
	private List<CotacaoMercadoriaItem> itens;
}
