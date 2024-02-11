package br.com.ultraworks.erp.api.seguranca.domain.contexto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextoDTO {

	private Long id;
	private Long usuarioId;
	private Long empresaId;
	private Long empresaFilialId;
	private Long moduloId;
	private String moduloPathBase;
	private String moduloNome;
	
}
