package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador.ValidaModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributariaDTO;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador.ValidaTipoCalculo;
import lombok.Data;

@Data
public class ConfiguracaoFiscalPisDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private SituacaoTributariaDTO situacaoTributaria;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculo;

	@ValidaTipoCalculo
	private String tipoCalculo;
	private BigDecimal aliquota;
	
	@ValidaTipoCalculo
	private String tipoCalculoST;
	private BigDecimal aliquotaST;
}
