package br.com.ultraworks.erp.api.compras.vo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoMercadoriaParaRetornoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 709601021268712659L;
	Long cotacaoMercadoriaId;
	Long cotacaoMercadoriaNumero;
	LocalDate cotacaoMercadoriaData;
	String cotacaoMercadoriaSituacao;
	String departamentoSigla;
	String parceiroNomeRazaoSocial;
	String parceiroLocalNome;
	Long cotacaoMercadoriaParceiroId;
	String situacaoCotacaoMercadoriaName;
	
}
