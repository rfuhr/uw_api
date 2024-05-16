package br.com.ultraworks.erp.api.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueResponse;
import br.com.ultraworks.erp.api.estoque.mapper.MovimentoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.repository.MovimentoEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MovimentoEstoqueService extends GenericService<MovimentoEstoque, Long, MovimentoEstoqueDTO> {
	
	MovimentoEstoqueRepository repository;
	
	@Autowired
	public MovimentoEstoqueService(MovimentoEstoqueRepository repository, MovimentoEstoqueMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	public List<MovimentoEstoqueResponse> buscaMovimentoEstoque(MovimentoEstoqueRequest movimentoEstoqueRequest) {
		String dataInicio = movimentoEstoqueRequest.getDataInicio().toString();
		String dataFinal = movimentoEstoqueRequest.getDataFinal().toString();
		int validaEmpresaFilial = movimentoEstoqueRequest.getEmpresaFilialId() == null ? 1 : 0;
		int validaLocalEstoque = movimentoEstoqueRequest.getLocalEstoqueId() == null ? 1 : 0;
		int validaGrupoContabil = movimentoEstoqueRequest.getGrupoContabilId() == null ? 1 : 0;
		int validaItem = movimentoEstoqueRequest.getItemId() == null ? 1 : 0;
		int validaOperacaoInterna = movimentoEstoqueRequest.getOperacaoInternaId() == null ? 1 : 0;
		int validaDocumento = movimentoEstoqueRequest.getDocumento() == null || movimentoEstoqueRequest.getDocumento() == "" ? 1 : 0;
		
		return repository.buscaMovimentoEstoqueResponse(dataInicio, dataFinal, validaEmpresaFilial, movimentoEstoqueRequest.getEmpresaFilialId(), validaLocalEstoque, 
				movimentoEstoqueRequest.getLocalEstoqueId(), validaGrupoContabil, movimentoEstoqueRequest.getGrupoContabilId(), validaItem, movimentoEstoqueRequest.getItemId(),
				validaOperacaoInterna, movimentoEstoqueRequest.getOperacaoInternaId(), validaDocumento, movimentoEstoqueRequest.getDocumento());
	}

}