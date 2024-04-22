package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.municipal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValoresISSQN {
	
	private Long iSSQN;
    private BigDecimal vBC;
    private BigDecimal vAliq;
    private BigDecimal vISSQN;
    private Long cMunFG;
    private Long cListServ;
    private BigDecimal vDeducao = BigDecimal.ZERO;
    private BigDecimal vOutro = BigDecimal.ZERO;
    private BigDecimal vDescIncond = BigDecimal.ZERO;
    private BigDecimal vDescCond;
    private BigDecimal vISSRet;
    private String indISS;
    private Long cServico;
    private Long cMun;
    private Long cPais;
    private String nProcesso;
    private String indIncentivo;
    protected BigDecimal vRetPIS = BigDecimal.ZERO;
    protected BigDecimal vRetCOFINS = BigDecimal.ZERO;
    protected BigDecimal vRetIR = BigDecimal.ZERO;
    protected BigDecimal vRetCSLL = BigDecimal.ZERO;
    protected BigDecimal vRetINSS = BigDecimal.ZERO;

}
