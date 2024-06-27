package br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira;

import br.com.ultraworks.erp.core.interfaces.IDataEstruturaContaResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataEstruturaPlanoClassificacaoFinanceira implements IDataEstruturaContaResponse {

	private Long id;
	private String codigo;
	private String nome;
	private boolean sintetica;
}
