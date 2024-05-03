package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalEntregaRequest {

	private String tipoPessoa;
	private String cnpj;
	private String cpf;
	private String nomeRazaoSocial;
	private String cep;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private Long paisId;
	private Long ufId;
	private Long cidadeId;
	private String telefone;
	private String email;
}
