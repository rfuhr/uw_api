package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;

@Data
public class NFeRequest {

	private Long nfeId;
	private IdentificacaoNFeRequest identificacaoNFeRequest;
	private DestinatarioNFeRequest destinatarioNFeRequest;
	private ItensNFeRequest itensNFeRequest;
	private TransporteNFeRequest transporteNFeRequest;
	private FinanceiroNFeRequest financeiroNFeRequest;
	private InformacoesAdicionaisNFeRequest informacoesAdicionaisNFeRequest;
}
