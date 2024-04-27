package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TributacaoIcmsNFeRequest {

	private Long configuracaoFiscalIcmsId;
	private String cst;
	private String modalidadeBaseCalculo;
	private BigDecimal reducaoBaseCalculo;
	private BigDecimal diferencialAliquota;
	private BigDecimal aliquota; 
	private BigDecimal valorBCIcms;
	private BigDecimal valorIcms;
	private String modalidadeBaseCalculoST;
	private BigDecimal margemValorAgregadoST;
	private BigDecimal reducaoBaseCalculoST;
	private BigDecimal valorBCIcmsST;
	private BigDecimal aliquotaST;
	private BigDecimal valorIcmsST;
	private Long motivoDesoneracaoId;
	private BigDecimal valorIcmsDesoneracao;
	private BigDecimal valorIcmsOperacao;
	private BigDecimal valorIcmsDiferido;
	private BigDecimal valorBCIcmsRetido;
	private BigDecimal valorIcmsProprioSubst;
	private BigDecimal valorIcmsRetido;
	private BigDecimal valorIcmsStRet;
	
	private BigDecimal aliquotaCredito;
	private BigDecimal valorCredIcmsSN;
}
