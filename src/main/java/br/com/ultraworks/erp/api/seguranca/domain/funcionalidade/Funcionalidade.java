package br.com.ultraworks.erp.api.seguranca.domain.funcionalidade;

import br.com.ultraworks.erp.api.seguranca.domain.modulo.Modulo;
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
@Table(name = "funcionalidade")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
public class Funcionalidade {

	@Id
	@SequenceGenerator(name = "funcionalidadeSeq", sequenceName = "seq_funcionalidade", allocationSize = 1)
	@GeneratedValue(generator = "funcionalidadeSeq")
	private Long id;

	private String nome;
	private String tag;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modulo_id")
	private Modulo modulo;
	
	private boolean crud;
}
