package br.com.ultraworks.erp.api.seguranca.domain.usuario;

import java.io.Serializable;
import java.util.List;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissaoDTO;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5603010371021261711L;

	private Long id;
	private String nome;
	private String email;
	private boolean admin;
	private boolean ativo;
	private String username;
	private String senha;
	private List<UsuarioPermissaoDTO> permissoes;
	private List<UsuarioFuncionalidadeDTO> funcionalidades;

}
