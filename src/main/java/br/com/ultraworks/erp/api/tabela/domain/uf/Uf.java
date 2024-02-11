package br.com.ultraworks.erp.api.tabela.domain.uf;

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
@Table(name = "uf")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Uf extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "ufSeq", sequenceName = "seq_uf", allocationSize = 1)
	@GeneratedValue(generator = "ufSeq")
	private Long id;

	private Long codigo;
	
	private String nome;
	
	private String sigla;
}