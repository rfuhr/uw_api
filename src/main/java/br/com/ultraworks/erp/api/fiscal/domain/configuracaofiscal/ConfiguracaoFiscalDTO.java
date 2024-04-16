package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal;

import java.time.LocalDate;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofinsDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcmsDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpiDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPisDTO;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ConfiguracaoFiscalDTO {

	private Long id;
	private Long ufOrigemId;
	private String ufOrigemNome;
	private String ufOrigemSigla;
	private Long paisDestinoId;
	private String paisDestinoNome;
	private Long ufDestinoId;
	private String ufDestinoNome;
	private String ufDestinoSigla;
	
	private String indicadorOperacao;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	
	private Long grupoTributacaoId;
	private String grupoTributacaoNome;
	private Long grupoTributacaoCodigo;
	private Long cfopId;
	private String cfopNome;
	private Long cfopCodigo;
	private Long ncmId;
	private String ncmNome;
	private String ncmCodigo;
	private Long regimeTributarioId;
	private String regimeTributarioNome;
	private Boolean regimeTributarioSimplesNacional;
	private Long origemId;
	private String origemNome;
	private Long origemCodigo;
	private Long operacaoInternaId;
	private String operacaoInternaNome;
	private String operacaoInternaSigla;
	private Long classificacaoOperacaoId;
	private String classificacaoOperacaoNome;
	private Long classificacaoOperacaoCodigo;
	private Long itemId;
	private String itemNome;
	private Long itemCodigo;
	
	@Valid
	private ConfiguracaoFiscalIcmsDTO configuracaoFiscalIcms;
	
	@Valid
	private ConfiguracaoFiscalIpiDTO configuracaoFiscalIpi;
	
	@Valid
	private ConfiguracaoFiscalPisDTO configuracaoFiscalPis;
	
	@Valid
	private ConfiguracaoFiscalCofinsDTO configuracaoFiscalCofins;
}
