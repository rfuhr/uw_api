package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CofinsNFeRequest {

	private Long configuracaoFiscalCofinsId;
	private String cst;
	private String tipoCalculo;
	private BigDecimal bcCofins;
	private BigDecimal aliquota;
	private BigDecimal valorCofins;
	private BigDecimal quantidadeVendida;
	private BigDecimal valorUnidade;
	private String tipoCalculoST;
	private BigDecimal aliquotaST;
	private BigDecimal valorUnidadeST;
	private BigDecimal valorCofinsST;

}
