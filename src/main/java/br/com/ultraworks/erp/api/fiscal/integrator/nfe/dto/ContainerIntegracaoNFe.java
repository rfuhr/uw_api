package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerIntegracaoNFe {

	private Long empresaFilialEmissorId;
	private ContainerIntegracaoNFeIdentificacao nfeIdentificacao;
	private ContainerIntegracaoNFeDestinatario nfeDestinatario;
	private ContainerIntegracaoNFeLocalRetirada nfeLocalRetirada;
	private ContainerIntegracaoNFeLocalEntrega nfeLocalEntrega;
	private List<ContainerIntegracaoNFeAutorizacao> nfeAutorizacaoXML;
	private List<ContainerIntegracaoNFeItem> nfeItens;
}
