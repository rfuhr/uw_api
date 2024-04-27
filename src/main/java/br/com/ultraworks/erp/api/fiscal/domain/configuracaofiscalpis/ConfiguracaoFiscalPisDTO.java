package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador.ValidaTipoCalculo;
import lombok.Data;

@Data
public class ConfiguracaoFiscalPisDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private Long situacaoTributariaId;
	private Long situacaoTributariaCodigo;
	private String situacaoTributariaNome;

	@ValidaTipoCalculo
	private String tipoCalculo;
	private BigDecimal aliquota;
	private BigDecimal valorUnidade;
	
	@ValidaTipoCalculo
	private String tipoCalculoST;
	private BigDecimal aliquotaST;
	private BigDecimal valorUnidadeST;
	
	private String tipoCalculoNome;
	private String tipoCalculoSTNome;
}
