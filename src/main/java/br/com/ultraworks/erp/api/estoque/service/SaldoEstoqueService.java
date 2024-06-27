package br.com.ultraworks.erp.api.estoque.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueResponse;
import br.com.ultraworks.erp.api.estoque.mapper.SaldoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.repository.SaldoEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SaldoEstoqueService extends GenericService<SaldoEstoque, Long, SaldoEstoqueDTO> {

	SaldoEstoqueRepository repository;
	
	@Autowired
	public SaldoEstoqueService(SaldoEstoqueRepository repository, SaldoEstoqueMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	@Transactional
	public void atualizaSaldoEstoque(Date dataInicio, Date dataFim, Long itemId, Long empresaFilialId,
			Long localEstoqueId, Long grupoContabilId, Long userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		repository.atualizaSaldoEstoque(sdf.format(dataInicio), sdf.format(dataFim), itemId, empresaFilialId, localEstoqueId, grupoContabilId, userId);
	}
	
	public List<SaldoEstoqueResponse> buscaSaldoEstoque(SaldoEstoqueRequest saldoEstoqueRequest) {
		String dataInicio = saldoEstoqueRequest.getDataInicio().toString();
		String dataFinal = saldoEstoqueRequest.getDataFinal().toString();
		int validaEmpresaFilial = saldoEstoqueRequest.getEmpresaFilialId() == null ? 1 : 0;
		int validaLocalEstoque = saldoEstoqueRequest.getLocalEstoqueId() == null ? 1 : 0;
		int validaGrupoContabil = saldoEstoqueRequest.getGrupoContabilId() == null ? 1 : 0;
		int validaItem = saldoEstoqueRequest.getItemId() == null ? 1 : 0;
		
		return repository.buscaSaldoEstoqueResponse(dataInicio, dataFinal, validaEmpresaFilial, saldoEstoqueRequest.getEmpresaFilialId(), validaLocalEstoque, saldoEstoqueRequest.getLocalEstoqueId(), 
				validaGrupoContabil, saldoEstoqueRequest.getGrupoContabilId(), validaItem, saldoEstoqueRequest.getItemId());
	}

}