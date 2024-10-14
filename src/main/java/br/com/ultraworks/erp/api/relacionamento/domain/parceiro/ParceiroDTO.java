package br.com.ultraworks.erp.api.relacionamento.domain.parceiro;

import java.util.List;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividadeDTO;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.validator.ValidaTipoPessoa;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import br.com.ultraworks.erp.core.validators.minlistsize.MinSize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParceiroDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 250)
	@FriendlyName(value = "Razão Social / Nome")
	private String nomeRazaoSocial;
	@Size(max = 120)
	@FriendlyName(value = "Nome Fantasia")
	private String nomeFantasia;
	@ValidaTipoPessoa
	private String tipoPessoa;
	@NotNull
	@NotEmpty
	@FriendlyName(value = "Raiz do CNPJ / CPF")
	private String raizCnpjCpf;

	@Valid
	@MinSize(min = 1)
	private List<ParceiroLocalDTO> locais;

	private List<ParceiroRegraAtividadeDTO> regras;
}
