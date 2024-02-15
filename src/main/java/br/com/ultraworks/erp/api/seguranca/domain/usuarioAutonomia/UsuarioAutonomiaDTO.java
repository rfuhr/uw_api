package br.com.ultraworks.erp.api.seguranca.domain.usuarioAutonomia;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioAutonomiaDTO {

    private long id;
    private Long usuarioId;
    private long empresaId;
    private List<Long> filiaisId;
    private long autonomiaId;

}
