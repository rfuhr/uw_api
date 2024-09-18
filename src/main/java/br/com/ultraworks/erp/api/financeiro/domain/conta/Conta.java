package br.com.ultraworks.erp.api.financeiro.domain.conta;

import br.com.ultraworks.erp.api.financeiro.domain.agencia.Agencia;
import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
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
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Conta extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "contaSeq", sequenceName = "seq_conta", allocationSize = 1)
	@GeneratedValue(generator = "contaSeq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "agencia_id")
	private Agencia agencia;
	
	private String numero;
	private String dv;
	private String nome;
	@OneToOne
	@JoinColumn(name = "tipo_conta_cxbco_id")
	private TipoContaCxBco tipoContaCxBco;
	
}
