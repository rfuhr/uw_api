package br.com.ultraworks.erp.api.fiscal.service.helper;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal.ValoresPIS;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;

public class CalcularPisHelper {
	
	public Imposto calcularPIS(Imposto imposto, ConfiguracaoFiscalPis configuracaoFiscalPis, CalculoImpostoRequest calculoImpostoRequest) {
		
		ValoresPIS valoresPIS = new ValoresPIS();
		valoresPIS.setCST(configuracaoFiscalPis.getSituacaoTributaria().getCodigo());
		
		if (!configuracaoFiscalPis.getSituacaoTributaria().isAliquotaZero()) {
			if (TipoCalculo.PERCENTUAL.getValue().equals(configuracaoFiscalPis.getTipoCalculo().getValue())) {
				valoresPIS.setPPIS(configuracaoFiscalPis.getAliquota());
				valoresPIS.setVBC(imposto.calcularBasePISCOFINS(imposto, calculoImpostoRequest));
				BigDecimal aliquota = configuracaoFiscalPis.getAliquota().divide(new BigDecimal(100));
				valoresPIS.setVPIS(valoresPIS.getVBC().multiply(aliquota));	
			} else if (TipoCalculo.VALOR.getValue().equals(configuracaoFiscalPis.getTipoCalculo().getValue())) {
				valoresPIS.setQBCProd(calculoImpostoRequest.getQuantidade());
				valoresPIS.setVAliqProd(configuracaoFiscalPis.getValorUnidade());
				valoresPIS.setVPIS(valoresPIS.getQBCProd().multiply(valoresPIS.getVAliqProd()));
			}
			
			// Cálculo ST
			if (configuracaoFiscalPis.getTipoCalculoST() != null) {
				if (TipoCalculo.PERCENTUAL.getValue().equals(configuracaoFiscalPis.getTipoCalculoST().getValue())) {
					BigDecimal aliquota = configuracaoFiscalPis.getAliquota().divide(new BigDecimal(100));
					valoresPIS.setVPISST(valoresPIS.getVBC().multiply(aliquota));	
				} else if (TipoCalculo.VALOR.getValue().equals(configuracaoFiscalPis.getTipoCalculoST().getValue())) {
					valoresPIS.setQBCProd(calculoImpostoRequest.getQuantidade());
					valoresPIS.setVAliqProdST(configuracaoFiscalPis.getValorUnidadeST());
					valoresPIS.setVPISST(calculoImpostoRequest.getQuantidade().multiply(configuracaoFiscalPis.getValorUnidadeST()));
				}
			}
		}
		
		imposto.setValoresPIS(valoresPIS);
		
		return imposto;
	}

}
