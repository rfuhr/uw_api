package br.com.ultraworks.erp.api.seguranca.domain.autonomia;

import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomia;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autonomia")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class Autonomia {

	@Id
	@SequenceGenerator(name = "autonomiaSeq", sequenceName = "seq_autonomia", allocationSize = 1)
	@GeneratedValue(generator = "autonomiaSeq")
	private Long id;

	private String nome;
	private String descricao;
	private String tag;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grupo_autonomia_id")
	private GrupoAutonomia grupoAutonomia;

}
