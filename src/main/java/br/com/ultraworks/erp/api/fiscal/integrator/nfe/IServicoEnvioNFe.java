package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;

public interface IServicoEnvioNFe {

	RetornoNFeIntegracao emitirNFe(ContainerIntegracaoNFe containerNFe);
}
