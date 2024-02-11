package br.com.ultraworks.erp.api.tabela.domain.profissao;

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
@Table(name = "profissao")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Profissao extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "profissaoSeq", sequenceName = "seq_profissao", allocationSize = 1)
	@GeneratedValue(generator = "profissaoSeq")
	private Long id;

	private Long codigo;
	
	private String nome;
}
