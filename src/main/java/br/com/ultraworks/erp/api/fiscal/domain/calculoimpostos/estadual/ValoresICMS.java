package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresICMS {
	
    private Integer orig;
    private Integer cST;
    private Integer cSOSN;
    private String modBC;
    private BigDecimal pRedBC;
    private BigDecimal vBC;
    private BigDecimal vBC_Desonerado;
    private BigDecimal pICMS;
    private BigDecimal pICMS_Desonerado;
    private BigDecimal vICMSOp;
    private BigDecimal pDif;
    private BigDecimal vICMSDif;
    private BigDecimal vICMS;
    private BigDecimal vICMS_Desonerado;
    private String modBCST;
    private BigDecimal pMVAST;
    private BigDecimal pRedBCST;
    private BigDecimal vBCST;
    private BigDecimal vBCST_NaoDestacado;
    private BigDecimal pICMSST;
    private BigDecimal vICMSST;
    private BigDecimal vICMSST_NaoDestacado;
    private String UFST;
    private BigDecimal pBCOp;
    private BigDecimal vBCSTRet;
    private BigDecimal vICMSSTRet;
    private BigDecimal vICMSDeson;
    private Integer motDesICMS;
    private BigDecimal pCredSN;
    private BigDecimal vCredICMSSN;
    private BigDecimal vBCSTDest;
    private BigDecimal vICMSSTDest;
    private BigDecimal vICMSFicto = BigDecimal.ZERO;
    private Boolean incluirFreteBC = Boolean.FALSE;
    private Boolean incluirIPIBC = Boolean.FALSE;
    private Boolean incluirFreteBCST = Boolean.FALSE;
    private Boolean incluirIPIBCST = Boolean.FALSE;
    
}
