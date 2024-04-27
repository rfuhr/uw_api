package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PisNFeRequest {

	private Long configuracaoFiscalPisId;
	private String cst;
	private String tipoCalculo;
	private BigDecimal bcPis;
	private BigDecimal aliquota;
	private BigDecimal valorPis;
	private BigDecimal quantidadeVendida;
	private BigDecimal valorUnidade;
	private String tipoCalculoST;
	private BigDecimal aliquotaST;
	private BigDecimal valorUnidadeST;
	private BigDecimal valorPisST;
	
}
