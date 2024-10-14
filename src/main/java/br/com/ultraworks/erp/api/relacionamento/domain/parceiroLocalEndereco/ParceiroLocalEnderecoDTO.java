package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco;

import java.io.Serializable;
import java.sql.Date;

import br.com.ultraworks.erp.api.tabela.domain.tipoendereco.validator.ValidaTipoEndereco;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.validator.ValidaTipoPessoa;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParceiroLocalEnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroLocalId;

	@ValidaTipoEndereco
	private String tipoEndereco;

	private String identificacao;

	private String cep;

	private Long cidadeId;

	private String cidadeNome;
	
	private Long ufId;
	private String ufSigla;
	private Long paisId;
	private String paisNome;

	@NotNull
	@NotEmpty
	@Size(max = 250)
	private String endereco;

	@Size(max = 120)
	private String complemento;

	@Size(max = 10)
	private String numero;

	@Size(max = 80)
	private String bairro;
	
	private boolean principal;

}
