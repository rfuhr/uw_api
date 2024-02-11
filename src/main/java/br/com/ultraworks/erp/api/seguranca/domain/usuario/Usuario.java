package br.com.ultraworks.erp.api.seguranca.domain.usuario;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import jakarta.persistence.Column;
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
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
public class Usuario extends UWEntityBase {

	@Id
	@SequenceGenerator(name = "usuarioSeq", sequenceName = "seq_usuario", allocationSize = 1)
	@GeneratedValue(generator = "usuarioSeq")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "admin")
	private boolean admin;
	@Column(name = "ativo")
	private boolean ativo;
}
