package br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola;

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
@Table(name = "fixacao_agricola")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class FixacaoAgricola extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "fixacaoAgricolaSeq", sequenceName = "seq_fixacao_agricola", allocationSize = 1)
	@GeneratedValue(generator = "fixacaoAgricolaSeq")
	private Long id;

}
