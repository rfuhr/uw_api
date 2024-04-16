package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador.ValidaModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador.ValidaTipoCalculo;
import lombok.Data;

@Data
public class ConfiguracaoFiscalIpiDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private Long situacaoTributariaId;
	private Long situacaoTributariaCodigo;
	private String situacaoTributariaNome;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculo;
	
	private Long enquadramentoId;
	private Long enquadramentoCodigo;
	private String enquadramentoNome;
	private BigDecimal aliquota;

	@ValidaTipoCalculo
	private String tipoCalculo;
	private String tipoCalculoName;
	
	private String codigoSelo;
	private int quantidadeSelo;
}
