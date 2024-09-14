package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parceiro_local_propriedade")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParceiroLocalPropriedade extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "parceiroLocalPropriedadeSeq", sequenceName = "seq_parceiro_local_propriedade", allocationSize = 1)
	@GeneratedValue(generator = "parceiroLocalPropriedadeSeq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;

	@Column(name = "identificacao")
	private String identificacao;

}
