package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinatarioNFeRequest {

	private Long destinatarioId;
	private Long enderecoId;
	private Long telefoneId;
	private Long emailId;
	private boolean outroLocalEntrega;
	private LocalEntregaRequest localEntrega;
}
