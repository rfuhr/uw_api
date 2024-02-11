package br.com.ultraworks.erp.api.seguranca.domain.perfil;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "perfil")
@Data
@EqualsAndHashCode(callSuper=false)
public class Perfil extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "perfilSeq", sequenceName = "seq_perfil", allocationSize = 1)
	@GeneratedValue(generator = "perfilSeq")
	private Long id;

	private String nome;
	private String descricao;

}
