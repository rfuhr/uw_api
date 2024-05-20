package br.com.ultraworks.erp.api.fiscal.service.helper;

import java.math.BigDecimal;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual.ValoresICMS;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;

public class CalcularIcmsHelper {
	
	public Imposto calcularICMS(Imposto imposto, ConfiguracaoFiscalIcms configuracaoFiscalIcms, CalculoImpostoRequest calculoImpostoRequest) {
		
		ValoresICMS valoresICMS = imposto.getValoresICMS();
		Boolean notaDevolucao = false; // TODO Implementar Devolução
		
		valoresICMS.setIncluirFreteBC(configuracaoFiscalIcms.isSomaFreteBaseCalculo());
		valoresICMS.setIncluirIPIBC(configuracaoFiscalIcms.isSomaIpiBaseCalculo());
		valoresICMS.setIncluirFreteBCST(configuracaoFiscalIcms.isSomaFreteBaseCalculoST());
		valoresICMS.setIncluirIPIBCST(configuracaoFiscalIcms.isSomaIpiBaseCalculoST());
		
		if (configuracaoFiscalIcms.getConfiguracaoFiscal().getRegimeTributario().isSimplesNacional()) {
			valoresICMS.setCSOSN(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo());
			
			if (!configuracaoFiscalIcms.getSituacaoTributaria().isAliquotaZero()) {
				/* Base/valor ficto do ICMS para fins de substituição tributária */
				BigDecimal vBC_ICMS_FICTO = imposto.calcularBaseICMSFicto(imposto, calculoImpostoRequest);
				BigDecimal aliquota = configuracaoFiscalIcms.getAliquotaCredito().divide(new BigDecimal(100));
				valoresICMS.setVICMSFicto(vBC_ICMS_FICTO.multiply(aliquota));
				
				/* Calcula valor de crédito do ICMS */
				BigDecimal vBC_ICMS_CredSN = imposto.calcularBaseICMS(imposto, calculoImpostoRequest);
				if (configuracaoFiscalIcms.getSituacaoTributaria().getCodigo() == 900 && notaDevolucao) {
					calcularTributacaoIntegral(valoresICMS, configuracaoFiscalIcms, imposto, calculoImpostoRequest);
				} else {
					valoresICMS.setPCredSN(configuracaoFiscalIcms.getAliquotaCredito());
					valoresICMS.setVCredICMSSN(vBC_ICMS_CredSN.multiply(aliquota));
				}
			}
		} else {
			calcularTributacaoIntegral(valoresICMS, configuracaoFiscalIcms, imposto, calculoImpostoRequest);
		}
		
		/* Calculo do ICMS-ST */
		if (configuracaoFiscalIcms.getModalidadeBaseCalculoST() != null) {
			valoresICMS.setModBCST(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue());
			valoresICMS.setPRedBCST(configuracaoFiscalIcms.getReducaoBaseCalculoST() != null ? configuracaoFiscalIcms.getReducaoBaseCalculoST() : BigDecimal.ZERO);
			valoresICMS.setPICMSST(configuracaoFiscalIcms.getAliquotaST());
			
			/* Margem Valor Agregado (%) */
			if (ModalidadeBaseCalculo.MVA.getValue().equals(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue())) {
				valoresICMS.setPMVAST(configuracaoFiscalIcms.getMargemValorAgregadoST());
				BigDecimal vBC_ICMS_ST = imposto.calcularBaseICMS(imposto, calculoImpostoRequest);
				BigDecimal percentualAjustado = (new BigDecimal(100).add(valoresICMS.getPMVAST())).divide(new BigDecimal(100));
				vBC_ICMS_ST = vBC_ICMS_ST.multiply(percentualAjustado);
				if (configuracaoFiscalIcms.getReducaoBaseCalculoST() != null && configuracaoFiscalIcms.getReducaoBaseCalculoST().doubleValue() > 0) {
					BigDecimal percentualReducao = (new BigDecimal(100).subtract(valoresICMS.getPMVAST())).divide(new BigDecimal(100));
					vBC_ICMS_ST = vBC_ICMS_ST.multiply(percentualReducao);
				}
				valoresICMS.setVBCST(vBC_ICMS_ST);
			} else {
				valoresICMS.setVBCST(imposto.calcularBaseICMS(imposto, calculoImpostoRequest));
			}
			
			BigDecimal aliquotaST = configuracaoFiscalIcms.getAliquotaST().divide(new BigDecimal(100));
			if (valoresICMS.getVICMSFicto() != null) {
				valoresICMS.setVICMSST((valoresICMS.getVBCST().multiply(aliquotaST)).subtract(valoresICMS.getVICMSFicto()));
			} else {
				valoresICMS.setVICMSST(valoresICMS.getVBCST().multiply(aliquotaST));
			}
			
			if (configuracaoFiscalIcms.getSituacaoTributaria().isDestacaStSaida()) {
				valoresICMS.setVBCST_NaoDestacado(valoresICMS.getVBCST());
				valoresICMS.setVICMSST_NaoDestacado(valoresICMS.getVICMSST());
				valoresICMS.setModBCST(null);
				valoresICMS.setPMVAST(null);
				valoresICMS.setPRedBCST(null);
				valoresICMS.setVBCST(null);
				valoresICMS.setPICMSST(null);
				valoresICMS.setVICMSST(null);
			}
			// TODO Implementar o Calculo da partilha do ICMS
		} 
		
		imposto.setValoresICMS(valoresICMS);
		
		return imposto;
	}

	private void calcularTributacaoIntegral(ValoresICMS valoresICMS, ConfiguracaoFiscalIcms configuracaoFiscalIcms,
			Imposto imposto, CalculoImpostoRequest calculoImpostoRequest) {
		
		if (configuracaoFiscalIcms.getSituacaoTributaria().isAliquotaZero()) {
			return;
		}
		
		BigDecimal vBC_ICMS = BigDecimal.ZERO;
		valoresICMS.setModBC(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue());
		if (ModalidadeBaseCalculo.MVA.getValue().equals(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue())
			|| ModalidadeBaseCalculo.VALOR.getValue().equals(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
		{
			vBC_ICMS = imposto.calcularBaseICMS(imposto, calculoImpostoRequest);
			//aplica MVA sobre ICMS próprio
			if (ModalidadeBaseCalculo.MVA.getValue().equals(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue())) {
				BigDecimal percentualAjustado = (new BigDecimal(100).add(valoresICMS.getPMVAST())).divide(new BigDecimal(100));
				vBC_ICMS = vBC_ICMS.multiply(percentualAjustado);
			}
			
		} else if (ModalidadeBaseCalculo.PAUTA.getValue().equals(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue())
				|| ModalidadeBaseCalculo.PRECO.getValue().equals(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
		{
			// TODO Implementar Pauta e Preço Tabelado
			vBC_ICMS = imposto.calcularBaseICMS(imposto, calculoImpostoRequest);
		}
		
		//calcula a redução da base de calculo apenas para os casos que possuem redução de base de calculo.
		if (configuracaoFiscalIcms.getReducaoBaseCalculo() != null && configuracaoFiscalIcms.getReducaoBaseCalculo().doubleValue() > 0) {
			valoresICMS.setPRedBC(configuracaoFiscalIcms.getReducaoBaseCalculo());
			BigDecimal percentualRedAjustado = (new BigDecimal(100).add(valoresICMS.getPRedBC())).divide(new BigDecimal(100));
			BigDecimal vBC_ICMS_Red = vBC_ICMS.multiply(percentualRedAjustado);
			BigDecimal aliquota = configuracaoFiscalIcms.getAliquota().divide(new BigDecimal(100));
			valoresICMS.setVICMSFicto(vBC_ICMS_Red.multiply(aliquota));
			
			if (configuracaoFiscalIcms.getSituacaoTributaria().isDestacaIcmsDesonerada()) {
				BigDecimal aliquotaCredito = configuracaoFiscalIcms.getAliquotaCredito().divide(new BigDecimal(100));
				BigDecimal vICMSNorm = vBC_ICMS.multiply(aliquotaCredito);
				valoresICMS.setVICMSDeson(vICMSNorm.subtract(valoresICMS.getVICMSFicto()));
				valoresICMS.setMotDesICMS(configuracaoFiscalIcms.getMotivoDesoneracao().getCodigo());
			}
			vBC_ICMS = vBC_ICMS_Red;
		} else {
			BigDecimal aliquota = configuracaoFiscalIcms.getAliquota().divide(new BigDecimal(100));
			valoresICMS.setVICMSFicto(vBC_ICMS.multiply(aliquota));
		}
		
		//destaca o ICMS apenas se estiver configurado para destacar o ICMS
		if (configuracaoFiscalIcms.getSituacaoTributaria().isDestacaIcms()) {
			valoresICMS.setVBC(vBC_ICMS);
			valoresICMS.setVICMS(valoresICMS.getVICMSFicto());
			valoresICMS.setPICMS(configuracaoFiscalIcms.getAliquota());
		}
		
		// TODO Implementar o Calculo da partilha do ICMS
		
		//calcula o Diferencial da Aliquota
		if (configuracaoFiscalIcms.getDiferencialAliquota() != null && configuracaoFiscalIcms.getDiferencialAliquota().doubleValue() > 0) {
			valoresICMS.setVBC(vBC_ICMS);
			valoresICMS.setPICMS(configuracaoFiscalIcms.getAliquota());
			valoresICMS.setVICMSOp(valoresICMS.getVICMSFicto());
			valoresICMS.setPDif(configuracaoFiscalIcms.getDiferencialAliquota());
			BigDecimal aliquotaDiferimento = configuracaoFiscalIcms.getDiferencialAliquota().divide(new BigDecimal(100));
			BigDecimal calculoParcial = valoresICMS.getVICMSFicto().multiply(aliquotaDiferimento);
			valoresICMS.setVICMSDif(valoresICMS.getVICMSFicto().subtract(calculoParcial));
			valoresICMS.setVICMS(valoresICMS.getVICMSFicto().subtract(valoresICMS.getVICMSDif()));
		}
	}

}

	