package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;

public interface IServicoInutilizacaoNFe {

	RetornoNFeIntegracao inutilizarNFe(NFe nfe);
}
