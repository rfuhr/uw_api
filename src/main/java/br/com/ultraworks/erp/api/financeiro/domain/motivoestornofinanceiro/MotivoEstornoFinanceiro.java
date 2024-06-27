package br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro;

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
@Table(name = "motivo_estorno_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class MotivoEstornoFinanceiro extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "motivoEstornoFinanceiroSeq", sequenceName = "seq_motivo_estorno_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "motivoEstornoFinanceiroSeq")
	private Long id;

	private String nome;
	private String sigla;
	
}
