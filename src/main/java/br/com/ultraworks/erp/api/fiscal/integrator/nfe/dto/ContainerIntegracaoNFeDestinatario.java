package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario.IndicadorIEDestinatario;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerIntegracaoNFeDestinatario {

	private ParceiroLocalEndereco parceiroLocalEndereco;
	private IndicadorIEDestinatario indicadorIEDestinatario;
}
