package br.com.ultraworks.erp.api.organograma.domain.organizacao;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "organizacao")
@Data
@EqualsAndHashCode(callSuper=false)
public class Organizacao extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "organizacaoSeq", sequenceName = "seq_organizacao", allocationSize = 1)
	@GeneratedValue(generator = "organizacaoSeq")
	private Long id;

	private String nome;
	private String sigla;
	private String tenant;
}
