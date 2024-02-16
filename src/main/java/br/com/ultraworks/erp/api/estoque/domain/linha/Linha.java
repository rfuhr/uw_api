package br.com.ultraworks.erp.api.estoque.domain.linha;

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
@Table(name = "linha")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Linha extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "linhaSeq", sequenceName = "seq_linha", allocationSize = 1)
	@GeneratedValue(generator = "linhaSeq")
	private Long id;

	private String nome;
}
