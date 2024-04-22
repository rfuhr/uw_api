package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresIPI {
	
	private Long clEnq;
    private String cNPJProd;
    private String cSelo;
    private Integer qSelo;
    private String cEnq;
    private Integer cST;
    private BigDecimal vBC;
    private BigDecimal qUnid;
    private BigDecimal vUnid;
    private BigDecimal pIPI;
    private BigDecimal vIPI;

}
