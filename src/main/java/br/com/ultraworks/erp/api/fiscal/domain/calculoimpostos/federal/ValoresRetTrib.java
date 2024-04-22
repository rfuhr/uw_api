package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresRetTrib {
	
    private BigDecimal vRetPIS;
    private BigDecimal vRetCOFINS;
    private BigDecimal vRetCSLL;
    private BigDecimal vBCIRRF;
    private BigDecimal vIRRF;
    private BigDecimal vBCRetPrev;
    private BigDecimal vRetPrev;

}
