package br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
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
@Table(name = "tipo_local_estoque")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe um Tipo de Local Estoque com este nome")
})
public class TipoLocalEstoque extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoLocalEstoqueSeq", sequenceName = "seq_tipo_local_estoque", allocationSize = 1)
	@GeneratedValue(generator = "tipoLocalEstoqueSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
}
