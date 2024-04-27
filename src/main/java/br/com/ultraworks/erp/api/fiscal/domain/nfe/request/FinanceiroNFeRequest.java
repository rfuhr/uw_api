package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinanceiroNFeRequest {

	private BigDecimal valorTroco;
	private PagamentosNFeRequest pagamentos;
}
