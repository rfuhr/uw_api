package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.municipal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresISSQNTot {
	
	private BigDecimal vServ;
	private BigDecimal vBC;
	private BigDecimal vISS;
	private BigDecimal vPIS;
	private BigDecimal vCOFINS;
	private BigDecimal vDeducao;
	private BigDecimal vOutro;
	private BigDecimal vDescIncond;
	private BigDecimal vDescCond;
	private BigDecimal vISSRet;

}
