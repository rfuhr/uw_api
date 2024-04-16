package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador.ValidaModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador.ValidaTipoCalculo;
import lombok.Data;

@Data
public class ConfiguracaoFiscalCofinsDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private Long situacaoTributariaId;
	private Long situacaoTributariaCodigo;
	private String situacaoTributariaNome;

	@ValidaTipoCalculo
	private String tipoCalculo;
	private BigDecimal aliquota;
	
	@ValidaTipoCalculo
	private String tipoCalculoST;
	private BigDecimal aliquotaST;
}
