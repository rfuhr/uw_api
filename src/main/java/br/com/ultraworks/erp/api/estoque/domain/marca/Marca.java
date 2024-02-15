package br.com.ultraworks.erp.api.estoque.domain.marca;

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
@Table(name = "marca")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Marca extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "marcaSeq", sequenceName = "seq_marca", allocationSize = 1)
	@GeneratedValue(generator = "marcaSeq")
	private Long id;

	private String nome;
}
