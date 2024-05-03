package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalhamentoItemNFeRequest {

	private Long itemId;
	private Long cfopId;
	private Long unidadeMedidaId;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal percentualDesconto;
	private BigDecimal valorDesconto;
	private BigDecimal valorFrete;
	private BigDecimal valorSeguro;
	private BigDecimal valorOutrasDespesas;
	private BigDecimal subTotal;
	private BigDecimal valorTotal;
	
}
