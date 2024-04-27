package br.com.ultraworks.erp.api.fiscal.domain.nfe;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.DestinatarioNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.FinanceiroNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.IdentificacaoNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.InformacoesAdicionaisNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.ItensNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.TransporteNFeRequest;
import lombok.Data;

@Data
public class NFeRequest {

	private Long nfeId;
	private IdentificacaoNFeRequest identificacaoNFe;
	private DestinatarioNFeRequest destinatarioNFe;
	private ItensNFeRequest itensNFeRequest;
	private TransporteNFeRequest transporteNFeRequest;
	private FinanceiroNFeRequest financeiroNFeRequest;
	private InformacoesAdicionaisNFeRequest informacoesAdicionaisNFeRequest;
}
