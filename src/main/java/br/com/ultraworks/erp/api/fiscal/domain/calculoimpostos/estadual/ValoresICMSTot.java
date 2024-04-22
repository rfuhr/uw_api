package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresICMSTot {
	
	private BigDecimal vBC = BigDecimal.ZERO;
    private BigDecimal vICMS = BigDecimal.ZERO;
    private BigDecimal vICMSDeson = BigDecimal.ZERO;
    private BigDecimal vFCPUFDest = BigDecimal.ZERO;
    private BigDecimal vICMSUFDest = BigDecimal.ZERO;
    private BigDecimal vICMSUFRemet = BigDecimal.ZERO;
    private BigDecimal vBCST = BigDecimal.ZERO;
    private BigDecimal vST = BigDecimal.ZERO;
    private BigDecimal vProd = BigDecimal.ZERO;
    private BigDecimal vFrete = BigDecimal.ZERO;
    private BigDecimal vSeg = BigDecimal.ZERO;
    private BigDecimal vDesc = BigDecimal.ZERO;
    private BigDecimal vII = BigDecimal.ZERO;
    private BigDecimal vIPI = BigDecimal.ZERO;
    private BigDecimal vPIS = BigDecimal.ZERO;
    private BigDecimal vCOFINS = BigDecimal.ZERO;
    private BigDecimal vOutro = BigDecimal.ZERO;
    private BigDecimal vNF = BigDecimal.ZERO;
    private BigDecimal vTotTrib = BigDecimal.ZERO;

}
