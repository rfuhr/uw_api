package br.com.ultraworks.erp.api.financeiro.domain.agencia;

import br.com.ultraworks.erp.api.financeiro.domain.banco.Banco;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
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
@Table(name = "agencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Agencia extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "agenciaSeq", sequenceName = "seq_agencia", allocationSize = 1)
	@GeneratedValue(generator = "agenciaSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "banco_id")
	private Banco banco;
	
	private int codigo;
	private int dv;
	private String nome;
}
