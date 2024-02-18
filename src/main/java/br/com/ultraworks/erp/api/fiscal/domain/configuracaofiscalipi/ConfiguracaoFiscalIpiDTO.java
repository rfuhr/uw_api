package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.EnquadramentoDTO;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.validador.ValidaModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributariaDTO;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.validador.ValidaTipoCalculo;
import lombok.Data;

@Data
public class ConfiguracaoFiscalIpiDTO {

	private Long id;
	private Long configuracaoFiscalId;
	private SituacaoTributariaDTO situacaoTributaria;
	
	@ValidaModalidadeBaseCalculo
	private String modalidadeBaseCalculo;
	
	private EnquadramentoDTO enquadramento;
	private BigDecimal aliquota;

	@ValidaTipoCalculo
	private String tipoCalculo;
	
	private String codigoSelo;
	private int quantidadeSelo;
}
