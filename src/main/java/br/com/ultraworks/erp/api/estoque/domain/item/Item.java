package br.com.ultraworks.erp.api.estoque.domain.item;

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
	
	@Column(name = "proprio")
	private boolean produtoProprio;

}
