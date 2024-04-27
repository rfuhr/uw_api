package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentosReferenciadosRequest {

	private String tipoDocumentoReferenciado;
	private String chaveAcesso;
	private Long ufIdEmitente;
	private String anoMes;
	private String cnpjEmitente;
	private String cpfEmitente;
	private String modeloDocumentoFiscal;
	private String serie;
	private int numero;
	private String numeroECF;
	private String numeroCOO;
}
