package br.com.ultraworks.erp.api.financeiro.domain.fatogerador;

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
@Table(name = "fato_gerador")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class FatoGerador extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "fatoGeradorSeq", sequenceName = "seq_fato_gerador", allocationSize = 1)
	@GeneratedValue(generator = "fatoGeradorSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;
	
}
