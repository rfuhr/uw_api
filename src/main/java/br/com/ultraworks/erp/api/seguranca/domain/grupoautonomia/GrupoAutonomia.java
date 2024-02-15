package br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia;

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
@Table(name = "grupo_autonomia")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
public class GrupoAutonomia {

	@Id
	@SequenceGenerator(name = "grupoAutonomiaSeq", sequenceName = "seq_grupo_autonomia", allocationSize = 1)
	@GeneratedValue(generator = "grupoAutonomiaSeq")
	private Long id;

	private String nome;
}
