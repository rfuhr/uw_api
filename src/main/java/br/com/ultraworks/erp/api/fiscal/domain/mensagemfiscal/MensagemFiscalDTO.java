package br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MensagemFiscalDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 2000)
	@FriendlyName("Nome")
	private String nome;
	@NotNull
	@NotEmpty
	@Size(max = 30)
	@FriendlyName("Sigla")
	private String sigla;
	@NotNull
	@NotEmpty
	@Size(max = 2000)
	@FriendlyName("Observação Fiscal")
	private String obsFiscal;
	@Size(max = 20)
	@FriendlyName("Código Ajuste")
	private String codigoAjuste;	
	private int codigo;
	private Long ufId;
	private String ufNome;
	private String ufSigla;
}
