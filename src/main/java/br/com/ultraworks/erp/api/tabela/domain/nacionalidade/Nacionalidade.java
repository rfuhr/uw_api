package br.com.ultraworks.erp.api.tabela.domain.nacionalidade;

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
@Table(name = "nacionalidade")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Nacionalidade extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "nacionalidadeSeq", sequenceName = "seq_nacionalidade", allocationSize = 1)
	@GeneratedValue(generator = "nacionalidadeSeq")
	private Long id;

	private Long codigo;
	
	private String nome;
}
