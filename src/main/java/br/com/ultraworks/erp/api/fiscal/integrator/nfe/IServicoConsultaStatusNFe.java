package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;

public interface IServicoConsultaStatusNFe {
	
	TRetConsSitNFe consultarStatusNFe(NFe nfe);

}
