package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentosNFeRequest {

	private String meioPagamento;
	private String indicadorFormaPagamento;
	private BigDecimal valorPagamento;
	private String tipoIntegracao;
	private String cnpj;
	private String bandeiraCartao;
	private String numeroAutorizacao;
}
