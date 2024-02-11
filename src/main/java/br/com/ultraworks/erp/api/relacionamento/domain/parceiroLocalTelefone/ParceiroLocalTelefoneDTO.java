package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone;

import java.io.Serializable;

import br.com.ultraworks.erp.api.tabela.domain.tipotelefone.validator.ValidaTipoTelefone;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParceiroLocalTelefoneDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroLocalId;

	@ValidaTipoTelefone
	private String tipoTelefone;

	private String identificacao;

	@Size(max = 40)
	private String numero;

}
