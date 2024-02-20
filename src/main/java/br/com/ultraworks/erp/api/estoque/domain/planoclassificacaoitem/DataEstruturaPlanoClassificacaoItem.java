package br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem;

import br.com.ultraworks.erp.core.interfaces.IDataEstruturaContaResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataEstruturaPlanoClassificacaoItem implements IDataEstruturaContaResponse {

	private Long id;
	private String codigo;
	private String nome;
	private boolean sintetica;
}
