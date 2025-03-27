package br.com.ultraworks.erp.api.fiscal.domain.nfe.response;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NFeComunicacaoSEFAZResponse {

	private Long id;
	private Long nfeId;
	private String tipoComunicacaoNfe;
	private String tipoComunicacaoNfeName;
	private String cstat;
	private String nprotnfe;
	private String nrecibo;
	private String xmotivo;
	private String status;
	private String xmlEnvio;
	private String xmlRetorno;
	
	private LocalDateTime criadoEm;

}
