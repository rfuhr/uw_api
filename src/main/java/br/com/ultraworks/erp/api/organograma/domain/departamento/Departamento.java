package br.com.ultraworks.erp.api.organograma.domain.departamento;

import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "departamento")
@Data
@EqualsAndHashCode(callSuper = false)
public class Departamento extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "departamentoSeq", sequenceName = "seq_departamento", allocationSize = 1)
	@GeneratedValue(generator = "departamentoSeq")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_filial_id")
	private EmpresaFilial empresaFilial;

	private String nome;
	private String sigla;
	private boolean general;
}
