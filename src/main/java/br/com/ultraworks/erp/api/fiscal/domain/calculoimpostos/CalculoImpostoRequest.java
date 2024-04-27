package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CalculoImpostoRequest {

	private Long empresaId;
	private Long itemId;
	private Long unidadeMedidaId;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorDesconto;
	private BigDecimal valorSeguro;
	private BigDecimal valorFrete;
	private BigDecimal valorOutros;
	private BigDecimal valorTotal;

	private Long configuracaoFiscalIcmsId;
	private Long configuracaoFiscalIpiId;
	private Long configuracaoFiscalPisId;
	private Long configuracaoFiscalCofinsId;

}
