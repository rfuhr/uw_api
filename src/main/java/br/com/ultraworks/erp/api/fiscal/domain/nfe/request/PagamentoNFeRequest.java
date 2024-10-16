package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentoNFeRequest {

	private String meioPagamento;
	private String indicadorFormaPagamento;
	private int numero;
	private LocalDate dataVencimento;
	private BigDecimal valorPagamento;
	private String tipoIntegracao;
	private String cnpj;
	private String bandeiraCartao;
	private String numeroAutorizacao;
}
