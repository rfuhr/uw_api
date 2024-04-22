package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresCOFINS {
	
    private Long cST;
    private BigDecimal vBC;
    private BigDecimal pCOFINS;
    private BigDecimal qBCProd;
    private BigDecimal vAliqProd;
    private BigDecimal vCOFINS;

}
