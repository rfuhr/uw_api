package br.com.ultraworks.erp.api.fiscal.domain.tipoproduto;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoProduto extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoProdutoSeq", sequenceName = "seq_tipo_produto", allocationSize = 1)
	@GeneratedValue(generator = "tipoProdutoSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
	@Column(name = "uso_consumo")
	private boolean usoConsumo;
	
}
