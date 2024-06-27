package br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoOperacaoFinanceiraDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	private String sigla;
	private boolean criaTitulo;
	private boolean baixaTitulo;
	private boolean selecionaBaixa;
	private String integracaoCaixaBanco;
	private Long operacaoCaixaBancoId;	
	private boolean selecionaSubstituicaoCarteira;
	private boolean selecionaNegociacao;
}
