package br.com.ultraworks.erp.api.tabela.domain.estadocivil;

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
@Table(name = "estado_civil")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class EstadoCivil extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "estadoCivilSeq", sequenceName = "seq_estado_civil", allocationSize = 1)
	@GeneratedValue(generator = "estadoCivilSeq")
	private Long id;

	private Long codigo;
	
	private String nome;
	
}
