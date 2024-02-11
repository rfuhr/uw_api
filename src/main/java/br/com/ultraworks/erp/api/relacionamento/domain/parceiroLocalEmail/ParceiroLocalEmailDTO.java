package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail;

import java.io.Serializable;

import br.com.ultraworks.erp.api.tabela.domain.tipoemail.validator.ValidaTipoEmail;
import lombok.Data;

@Data
public class ParceiroLocalEmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroLocalId;

	@ValidaTipoEmail
	private String tipoEmail;

	private String identificacao;

	private String email;

}
