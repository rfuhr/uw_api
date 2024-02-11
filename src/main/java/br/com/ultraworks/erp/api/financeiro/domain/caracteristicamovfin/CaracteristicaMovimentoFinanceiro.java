package br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin;

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
@Table(name = "caracteristica_movimento_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class CaracteristicaMovimentoFinanceiro extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "caracteristicaMovFinSeq", sequenceName = "seq_caracteristica_movimento_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "caracteristicaMovFinSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;
	
}
