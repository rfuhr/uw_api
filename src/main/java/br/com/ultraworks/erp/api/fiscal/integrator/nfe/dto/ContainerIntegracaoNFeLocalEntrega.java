package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerIntegracaoNFeLocalEntrega {

	private String cnpj;
	private String cpf;
	private String razaoSocialNome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Cidade municipio;
	private String telefone;
	private String email;
	private String inscricaoEstadual;

}
