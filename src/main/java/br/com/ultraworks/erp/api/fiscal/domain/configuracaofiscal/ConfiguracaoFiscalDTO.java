package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.entradasaida.validador.ValidaEntradaSaida;
import lombok.Data;

@Data
public class ConfiguracaoFiscalDTO {

	private Long id;
	private Long ufOrigemId;
	private String ufOrigemNome;
	private Long paisDestinoId;
	private String paisDestinoNome;
	private Long ufDestinoId;
	private String ufDestinoNome;
	
	@ValidaEntradaSaida
	private String entradaSaida;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	private Long grupoTributacaoId;
	private String grupoTributacaoNome;
	private Long cfopId;
	private String cfopNome;
	private Long ncmId;
	private String ncmNome;
	private Long regimeTributarioId;
	private String regimeTributarioNome;
	private Long origemId;
	private String origemNome;
	private Long operacaoInternaId;
	private String operacaoInternaNome;
	private Long classificacaoOperacaoId;
	private String classificacaoOperacaoNome;
	private Long itemId;
	private String itemNome;
}
