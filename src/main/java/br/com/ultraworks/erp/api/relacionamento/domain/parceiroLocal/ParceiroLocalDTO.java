package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal;

import java.io.Serializable;
import java.util.List;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmailDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEnderecoDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisicaDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridicaDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefoneDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiroDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParceiroLocalDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroId;
	private String nomeRazaoSocial;

	@NotNull
	@NotEmpty
	@Size(min = 8, max = 14)
	private String cpfCnpj;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 120)
	private String nomeLocal;

	@Valid
	private ParceiroLocalFisicaDTO dadosPessoaFisica;

	@Valid
	private ParceiroLocalJuridicaDTO dadosPessoaJuridica;

	@Valid
	private List<ParceiroLocalTipoParceiroDTO> tiposParceiro;

	@Valid
	private List<ParceiroLocalEnderecoDTO> enderecos;

	@Valid
	private List<ParceiroLocalTelefoneDTO> telefones;

	@Valid
	private List<ParceiroLocalEmailDTO> emails;

}
