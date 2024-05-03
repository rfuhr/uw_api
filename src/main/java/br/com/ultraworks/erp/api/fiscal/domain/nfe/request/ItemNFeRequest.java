package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemNFeRequest {

	private Long idLine;
	private DetalhamentoItemNFeRequest detalhamentoItem;
	private TributacaoIcmsNFeRequest tributacaoIcms;
	private IpiNFeRequest ipi;
	private PisNFeRequest pis;
	private CofinsNFeRequest cofins;
	private String infoAdicionais;
	private Long configuracaoFiscalId;
	
}
