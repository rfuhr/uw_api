package br.com.ultraworks.erp.api.fiscal.domain.nfe.response;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEnderecoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NovaNFeResponse {

	private Long empresaFilialId;
	private Long regimeTributarioId;
	private String tipoAmbiente;
	private String tipoEmissao;
	private int serie;
	private int numero;
	private String chaveNFe;
	private ParceiroLocalEnderecoDTO enderecoEmitente;
	private Long nfeId;
}
