package br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.Funcionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.perfil.Perfil;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "perfil_funcionalidade")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@EqualsAndHashCode(callSuper=false)
public class PerfilFuncionalidade  extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "perfilFuncionalidadeSeq", sequenceName = "seq_perfil_funcionalidade", allocationSize = 1)
	@GeneratedValue(generator = "perfilFuncionalidadeSeq")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionalidade_id")
	private Funcionalidade funcionalidade;

	private boolean consultar;
	private boolean inserir;
	private boolean alterar;
	private boolean excluir;
}
