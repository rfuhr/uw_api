package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;

public interface IServicoIntegracaoNFe {

	RetornoNFeIntegracao emitirNFe(ContainerIntegracaoNFe containerNFe);
}
