package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresICMSUFDest {
	
	private BigDecimal vBCUFDest;
    private BigDecimal pFCPUFDest;
    private BigDecimal pICMSUFDest;
    private BigDecimal pICMSInter;
    private BigDecimal pICMSInterPart;
    private BigDecimal vFCPUFDest;
    private BigDecimal vICMSUFDest;
    private BigDecimal vICMSUFRemet;

}
