package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IpiNFeRequest {

	private Long configuracaoFiscalIpiId;
	private String cst;
	private String cnpjProdutor;
	private String codigoSelo;
	private String quantidadeSelo;
	private String tipoCalculo;
	private Long enquadramentoId;
	private BigDecimal bcIpi;
	private BigDecimal aliquota;
	private BigDecimal quantidade;
	private BigDecimal valorUnidade;
	private BigDecimal valorIpi;
}
