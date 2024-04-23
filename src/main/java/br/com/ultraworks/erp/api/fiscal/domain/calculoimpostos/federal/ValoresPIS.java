package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresPIS {
	
    private int cST;
    private BigDecimal vBC;
    private BigDecimal pPIS;
    private BigDecimal qBCProd;
    private BigDecimal vAliqProd;
    private BigDecimal vPIS;
    private BigDecimal vPISST;
    private BigDecimal vAliqProdST;

}
