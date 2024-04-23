package br.com.ultraworks.erp.api.fiscal.service.helper;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal.ValoresIPI;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;

public class CalcularIpiHelper {
	
	public Imposto calcularIPI(Imposto imposto, ConfiguracaoFiscalIpi configuracaoFiscalIpi, CalculoImpostoRequest calculoImpostoRequest) {
		
		ValoresIPI valoresIPI = new ValoresIPI();
		valoresIPI.setCST(configuracaoFiscalIpi.getSituacaoTributaria().getCodigo());
		valoresIPI.setCEnq(configuracaoFiscalIpi.getEnquadramento().getCodigo());
		
		if (!configuracaoFiscalIpi.getSituacaoTributaria().isAliquotaZero()) {
			if (TipoCalculo.ALIQUOTA.getValue().equals(configuracaoFiscalIpi.getTipoCalculo().getValue())) {
				valoresIPI.setVBC(calculoImpostoRequest.getValorUnitario().subtract(calculoImpostoRequest.getValorDesconto()));
				valoresIPI.setPIPI(configuracaoFiscalIpi.getAliquota());
				valoresIPI.setVIPI(valoresIPI.getVBC().divide(configuracaoFiscalIpi.getAliquota().divide(new BigDecimal(100))));	
			} else if (TipoCalculo.QUANTIDADE.getValue().equals(configuracaoFiscalIpi.getTipoCalculo().getValue())) {
				valoresIPI.setQUnid(calculoImpostoRequest.getQuantidade());
				valoresIPI.setVUnid(configuracaoFiscalIpi.getValorUnidade());
				valoresIPI.setVIPI(valoresIPI.getQUnid().multiply(valoresIPI.getVUnid()));
			}
		}
		
		imposto.setValoresIPI(valoresIPI);
		
		return imposto;
	}

}
