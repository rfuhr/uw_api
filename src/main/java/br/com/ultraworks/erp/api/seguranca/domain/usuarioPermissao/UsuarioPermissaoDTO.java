package br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioPermissaoDTO {

    private long id;
    private Long usuarioId;
    private long empresaId;
    private List<Long> filiaisId;
    private List<Long> perfisId;
    
}
