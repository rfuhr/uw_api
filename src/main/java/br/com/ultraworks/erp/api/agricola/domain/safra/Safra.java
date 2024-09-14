package br.com.ultraworks.erp.api.agricola.domain.safra;

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
@Table(name = "safra")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@UniqueValidationGroup({
    @UniqueValidation(fields = {"nome"}, label = "Já existe uma Safra com este nome")
})
public class Safra extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "safraSeq", sequenceName = "seq_safra", allocationSize = 1)
	@GeneratedValue(generator = "safraSeq")
	private Long id;

	private String nome;
	
}
