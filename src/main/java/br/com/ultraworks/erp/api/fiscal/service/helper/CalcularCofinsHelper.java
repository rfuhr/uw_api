package br.com.ultraworks.erp.api.fiscal.service.helper;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal.ValoresCOFINS;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;

public class CalcularCofinsHelper {
	
	public Imposto calcularCOFINS(Imposto imposto, ConfiguracaoFiscalCofins configuracaoFiscalCofins, CalculoImpostoRequest calculoImpostoRequest) {
		
		ValoresCOFINS valoresCOFINS = new ValoresCOFINS();
		valoresCOFINS.setCST(configuracaoFiscalCofins.getSituacaoTributaria().getCodigo());
		
		if (!configuracaoFiscalCofins.getSituacaoTributaria().isAliquotaZero()) {
			if (TipoCalculo.PERCENTUAL.getValue().equals(configuracaoFiscalCofins.getTipoCalculo().getValue())) {
				valoresCOFINS.setPCOFINS(configuracaoFiscalCofins.getAliquota());
				valoresCOFINS.setVBC(imposto.calcularBasePISCOFINS(imposto, calculoImpostoRequest));
				BigDecimal aliquota = configuracaoFiscalCofins.getAliquota().divide(new BigDecimal(100));
				valoresCOFINS.setVCOFINS(valoresCOFINS.getVBC().multiply(aliquota));	
			} else if (TipoCalculo.VALOR.getValue().equals(configuracaoFiscalCofins.getTipoCalculo().getValue())) {
				valoresCOFINS.setQBCProd(calculoImpostoRequest.getQuantidade());
				valoresCOFINS.setVAliqProd(configuracaoFiscalCofins.getValorUnidade());
				valoresCOFINS.setVCOFINS(valoresCOFINS.getQBCProd().multiply(valoresCOFINS.getVAliqProd()));
			}
			
			// Cálculo ST
			if (configuracaoFiscalCofins.getTipoCalculoST() != null) {
				if (TipoCalculo.PERCENTUAL.getValue().equals(configuracaoFiscalCofins.getTipoCalculoST().getValue())) {
					BigDecimal aliquota = configuracaoFiscalCofins.getAliquota().divide(new BigDecimal(100));
					valoresCOFINS.setVCOFINSST(valoresCOFINS.getVBC().multiply(aliquota));	
				} else if (TipoCalculo.VALOR.getValue().equals(configuracaoFiscalCofins.getTipoCalculoST().getValue())) {
					valoresCOFINS.setQBCProd(calculoImpostoRequest.getQuantidade());
					valoresCOFINS.setVAliqProdST(configuracaoFiscalCofins.getValorUnidadeST());
					valoresCOFINS.setVCOFINSST(calculoImpostoRequest.getQuantidade().multiply(configuracaoFiscalCofins.getValorUnidadeST()));
				}
			}
		}
		
		imposto.setValoresCOFINS(valoresCOFINS);
		
		return imposto;
	}

}
