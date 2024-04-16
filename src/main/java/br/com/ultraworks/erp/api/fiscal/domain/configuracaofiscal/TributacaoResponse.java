package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofinsDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcmsDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpiDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPisDTO;
import lombok.Data;

@Data
public class TributacaoResponse {

	private boolean temIcms;
	private boolean temPartilhaIcms;
	private boolean temRepasseIcms;
	private boolean temIcmsParaUfDestino;
	private boolean temIpi;
	private boolean temII;
	private boolean temPis;
	private boolean temPisSt;
	private boolean temCofins;
	private boolean temCofinsSt;
	
	private ConfiguracaoFiscalIcmsDTO configuracaoFiscalIcms;
	private ConfiguracaoFiscalIpiDTO configuracaoFiscalIpi;
	private ConfiguracaoFiscalPisDTO configuracaoFiscalPis;
	private ConfiguracaoFiscalCofinsDTO configuracaoFiscalCofins;
}
