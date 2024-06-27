package br.com.ultraworks.erp.api.financeiro.domain.banco;

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
@Table(name = "banco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Banco extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "bancoSeq", sequenceName = "seq_banco", allocationSize = 1)
	@GeneratedValue(generator = "bancoSeq")
	private Long id;

	private int codigo;
	private int dv;
	private String nome;
	private String sigla;
	
}
