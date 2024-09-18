package br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco;

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
@Table(name = "tipo_conta_cxbco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoContaCxBco extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "tipoContaCxBcoSeq", sequenceName = "seq_tipo_conta_cxbco", allocationSize = 1)
	@GeneratedValue(generator = "tipoContaCxBcoSeq")
	private Long id;

	private int codigo;
	private String nome;
	
}
