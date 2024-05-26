package br.com.ultraworks.erp.api.comercial.domain.tipopreco;

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
@Table(name = "tipo_preco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"codigo"}, label = "Já existe um código com este valor"),
    @UniqueValidation(fields = {"nome"}, label = "Já existe um Tipo de Preço com este nome")
})
public class TipoPreco extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoPrecoSeq", sequenceName = "seq_tipo_preco", allocationSize = 1)
	@GeneratedValue(generator = "tipoPrecoSeq")
	private Long id;

	private int codigo;
	
	private String nome;
	
	private boolean transferencia;
	
}
