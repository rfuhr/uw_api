package br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro;

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
@Table(name = "grupo_financeiro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class GrupoFinanceiro extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "grupoFinanceiroSeq", sequenceName = "seq_grupo_financeiro", allocationSize = 1)
	@GeneratedValue(generator = "grupoFinanceiroSeq")
	private Long id;

	private int codigo;
	private String nome;
	private String sigla;
	
}
