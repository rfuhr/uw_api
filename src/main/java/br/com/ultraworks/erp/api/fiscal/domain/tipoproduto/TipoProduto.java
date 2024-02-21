package br.com.ultraworks.erp.api.fiscal.domain.tipoproduto;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
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
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe Tipo de Produto com este código"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe Tipo de Produto com este nome")
})
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
