package br.com.ultraworks.erp.api.configuracao.domain.certificado;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpresaCertificadoDTO {

	private Long id;

	@NotNull
	@FriendlyName("Empresa")
	private Long empresaId;
	private String tipoCertificado;
	private LocalDate dataValidade;
	private String senha;

}
