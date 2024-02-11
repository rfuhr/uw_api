package br.com.ultraworks.erp.api.seguranca.domain.modulo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modulo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Modulo {

	@Id
	@SequenceGenerator(name = "moduloSeq", sequenceName = "seq_modulo", allocationSize = 1)
	@GeneratedValue(generator = "moduloSeq")
	private Long id;

	private String nome;

	private String sigla;

	private String icone;

	@Column(name = "path_base")
	private String pathBase;
}
