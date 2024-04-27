package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador.ValidaModalidadeBaseCalculo;
import lombok.Data;

@Data
public class ConfiguracaoFiscalIcmsDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private Long situacaoTributariaId;
	private Long situacaoTributariaCodigo;
	private String situacaoTributariaNome;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculo;
	
	private Long motivoDesoneracaoId;
	private Long motivoDesoneracaoCodigo;
	private String motivoDesoneracaoNome;
	private BigDecimal reducaoBaseCalculo;
	private boolean somaIpiBaseCalculo;
	private boolean somaFreteBaseCalculo;
	private BigDecimal aliquota;
	private BigDecimal aliquotaCredito;
	private BigDecimal diferencialAliquota;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculoST;
	
	private Long motivoDesoneracaoSTId;
	private Long motivoDesoneracaoSTCodigo;
	private String motivoDesoneracaoSTNome;
	private BigDecimal reducaoBaseCalculoST;
	private boolean somaIpiBaseCalculoST;
	private boolean somaFreteBaseCalculoST;
	private BigDecimal aliquotaST;
	private BigDecimal margemValorAgregadoST;
}
