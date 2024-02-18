package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador.ValidaModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracaoDTO;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributariaDTO;
import lombok.Data;

@Data
public class ConfiguracaoFiscalIcmsDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private SituacaoTributariaDTO situacaoTributaria;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculo;
	
	private MotivoDesoneracaoDTO motivoDesoneracao;
	private BigDecimal reducaoBaseCalculo;
	private boolean somaIpiBaseCalculo;
	private BigDecimal aliquota;
	private BigDecimal aliquotaCredito;
	private BigDecimal diferencialAliquota;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculoST;
	
	private MotivoDesoneracaoDTO motivoDesoneracaoST;
	private BigDecimal reducaoBaseCalculoST;
	private boolean somaIpiBaseCalculoST;
	private BigDecimal aliquotaST;
	private BigDecimal margemValorAgregadoST;
}
