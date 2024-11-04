package br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "valida_departamento_grupo_contabil")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ValidaDepartamentoGrupoContabil extends UWEntityBase {
	
	@Id
	@SequenceGenerator(name = "validaDepartamentoGrupoContabilSeq", sequenceName = "seq_valida_departamento_grupo_contabil", allocationSize = 1)
	@GeneratedValue(generator = "validaDepartamentoGrupoContabilSeq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	
	@OneToOne
	@JoinColumn(name = "grupo_contabil_id")
	private GrupoContabil grupoContabil;
	
	@Column(name = "controla_estoque")
	private boolean controlaEstoque;
}
