package br.com.ultraworks.erp.api.organograma.domain.empresaFilial;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
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
@Table(name = "empresa_filial")
@Data
@EqualsAndHashCode(callSuper=false)
public class EmpresaFilial extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "empresaFilialSeq", sequenceName = "seq_empresa_filial", allocationSize = 1)
	@GeneratedValue(generator = "empresaFilialSeq")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	private String nome;
	private String sigla;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parceiro_local_id")
	private ParceiroLocal parceiroLocal;
	
	private boolean general;
}
