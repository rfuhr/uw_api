package br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioFuncionalidadeDTO {

    private long id;
    private Long usuarioId;
    private long empresaId;
    private List<Long> filiaisId;
    private long funcionalidadeId;
	private boolean consultar;
	private boolean inserir;
	private boolean alterar;
	private boolean excluir;
	private boolean liberado;
}
