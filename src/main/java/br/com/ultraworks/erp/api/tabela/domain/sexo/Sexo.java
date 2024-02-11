package br.com.ultraworks.erp.api.tabela.domain.sexo;

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
@Table(name = "sexo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Sexo extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "sexoSeq", sequenceName = "seq_sexo", allocationSize = 1)
	@GeneratedValue(generator = "sexoSeq")
	private Long id;
	
	private String nome;
}
