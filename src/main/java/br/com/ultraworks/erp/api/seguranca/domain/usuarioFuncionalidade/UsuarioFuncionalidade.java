package br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade;

import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "usuario_funcionalidade")
@Data
@EqualsAndHashCode(callSuper=false)
public class UsuarioFuncionalidade extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "usuarioFuncionalidadeSeq", sequenceName = "seq_usuario_funcionalidade", allocationSize = 1)
	@GeneratedValue(generator = "usuarioFuncionalidadeSeq")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;	
	
	@Column(name = "empresa_id")
	private int empresaId;
	
	@Column(name = "filiais_id")
	private String filiaisId;
	
	@Column(name = "funcionalidade_id")
	private int funcionalidadeId;
	
	private boolean consultar;
	private boolean inserir;
	private boolean alterar;
	private boolean excluir;
	private boolean liberado;
}
