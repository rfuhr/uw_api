package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.repository.SaldoEstoqueRepository;
import br.com.ultraworks.erp.api.estoque.service.GrupoContabilService;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.estoque.service.LocalEstoqueService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SaldoEstoqueMapper extends GenericMapper<SaldoEstoque, SaldoEstoqueDTO> {

	LocalEstoqueService localEstoqueService;
	EmpresaFilialService empresaFilialService;
	GrupoContabilService grupoContabilService;
	ItemService itemService;
	
	public SaldoEstoqueMapper(SaldoEstoqueRepository repository,
			LocalEstoqueService localEstoqueService,
			EmpresaFilialService empresaFilialService,
			GrupoContabilService grupoContabilService,
			ItemService itemService) {
		super(repository, SaldoEstoque::new, SaldoEstoqueDTO::new);
		this.localEstoqueService = localEstoqueService;
		this.empresaFilialService = empresaFilialService;
		this.grupoContabilService = grupoContabilService;
		this.itemService = itemService;
	}

	@Override
	protected void setValuesToEntity(SaldoEstoqueDTO dto, SaldoEstoque entity) {
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setSaldoQuantidade(dto.getSaldoQuantidade());
		entity.setSaldoValor(dto.getSaldoValor());
		entity.setCustoMedio(dto.getCustoMedio());
		entity.setLocalEstoque(localEstoqueService.getById(dto.getLocalEstoqueId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado Local de Estoque com id " + dto.getLocalEstoqueId())));
		entity.setEmpresaFilial(empresaFilialService.getById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrada a Filial com id " + dto.getEmpresaFilialId())));	
		entity.setGrupoContabil(grupoContabilService.getById(dto.getGrupoContabilId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado o Grupo Contábil com id " + dto.getGrupoContabilId())));
		entity.setItem(itemService.getById(dto.getItemId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado o Item com id " + dto.getItemId())));
	}

	@Override
	protected void setValuesToDto(SaldoEstoque entity, SaldoEstoqueDTO dto) {
		dto.setId(entity.getId());
		dto.setData(entity.getData());
		dto.setSaldoQuantidade(entity.getSaldoQuantidade());
		dto.setSaldoValor(entity.getSaldoValor());
		dto.setCustoMedio(entity.getCustoMedio());
		dto.setLocalEstoqueId(entity.getLocalEstoque().getId());
		dto.setLocalEstoqueCodigo(entity.getLocalEstoque().getCodigo());
		dto.setLocalEstoqueNome(entity.getLocalEstoque().getNome());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setEmpresaFilialSigla(entity.getEmpresaFilial().getSigla());
		dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		dto.setGrupoContabilId(entity.getGrupoContabil().getId());
		dto.setGrupoContabilCodigo(entity.getGrupoContabil().getCodigo());
		dto.setGrupoContabilNome(entity.getGrupoContabil().getNome());
		dto.setItemId(entity.getItem().getId());
		dto.setItemCodigo(entity.getItem().getCodigo());
		dto.setItemNome(entity.getItem().getNome());
	}
}
