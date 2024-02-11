package br.com.ultraworks.erp.api.organograma.domain.empresa;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "empresa")
@Data
@EqualsAndHashCode(callSuper=false)
public class Empresa extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "empresaSeq", sequenceName = "seq_empresa", allocationSize = 1)
	@GeneratedValue(generator = "empresaSeq")
	private Long id;

	private String nome;
	private String sigla;

}
