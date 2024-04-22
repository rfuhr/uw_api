package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CalculoImpostoRequest {

	private String cnpjProdutor;
	private Long itemId;
	private Integer codigoOrigem;
	private String siglaUnidadeMedida;
	private BigDecimal quantidade;
	private BigDecimal valor;
	private BigDecimal valorDesconto;
	private BigDecimal valorSeguro;
	private BigDecimal valorFrete;
	private BigDecimal valorOutros;

	private Long configuracaoFiscalIcmsId;
	private Long configuracaoFiscalIpiId;
	private Long configuracaoFiscalPisId;
	private Long configuracaoFiscalCofinsId;

}
