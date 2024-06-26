package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual.ValoresICMS;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual.ValoresICMSUFDest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal.ValoresCOFINS;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal.ValoresIPI;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.federal.ValoresPIS;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.municipal.ValoresISSQN;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Imposto {
	
	public BigDecimal vTotTrib;
    public BigDecimal vTotTribFederal;
    public BigDecimal vTotTribEstadual;
    public BigDecimal vTotTribMunicipal;
    public ValoresICMS valoresICMS;
    public ValoresICMSUFDest valoresICMSUFDest;
    public ValoresIPI valoresIPI;
//    public ValoresII II;
    public ValoresPIS valoresPIS;
    public ValoresCOFINS valoresCOFINS;
    public ValoresISSQN valoresISSQN;
    public Boolean contribuinteIPI;

    public BigDecimal calcularBasePISCOFINS (Imposto impostoCalculo, CalculoImpostoRequest calculoImpostoRequest) {
    	BigDecimal base = calculoImpostoRequest.getValorTotal()
    			.add(calculoImpostoRequest.getValorFrete())
    			.add(calculoImpostoRequest.getValorSeguro())
    			.add(calculoImpostoRequest.getValorOutros())
    			.subtract(calculoImpostoRequest.getValorDesconto());
    	
    	if (impostoCalculo.getValoresICMS() != null && impostoCalculo.getValoresICMS().getVICMS() != null) {
    		base = base.subtract(impostoCalculo.getValoresICMS().getVICMS());
    	}
    	
    	return base;
    }
    
    public BigDecimal calcularBaseICMSFicto (Imposto impostoCalculo, CalculoImpostoRequest calculoImpostoRequest) {
    	BigDecimal base = calculoImpostoRequest.getValorTotal()
    			.add(calculoImpostoRequest.getValorFrete())
    			.add(calculoImpostoRequest.getValorSeguro())
    			.add(calculoImpostoRequest.getValorOutros())
    			.subtract(calculoImpostoRequest.getValorDesconto());
    	
    	return base;
    }
    
    public BigDecimal calcularBaseICMS (Imposto impostoCalculo, CalculoImpostoRequest calculoImpostoRequest) {
    	BigDecimal base = calculoImpostoRequest.getValorTotal()
    			.add(calculoImpostoRequest.getValorSeguro())
    			.add(calculoImpostoRequest.getValorOutros())
    			.subtract(calculoImpostoRequest.getValorDesconto());
    	
    	if (impostoCalculo.getValoresICMS().getIncluirFreteBC()) {
    		base = base.add(calculoImpostoRequest.getValorFrete());
    	}
    	
    	if (impostoCalculo.getValoresICMS().getIncluirIPIBC() && impostoCalculo.getContribuinteIPI() 
    			&& impostoCalculo.getValoresIPI().getVIPI() != null) {
    		base = base.add(impostoCalculo.getValoresIPI().getVIPI());
    	}
    	
    	return base;
    }
    
}
