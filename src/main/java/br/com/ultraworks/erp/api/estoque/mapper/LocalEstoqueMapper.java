package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.repository.LocalEstoqueRepository;
import br.com.ultraworks.erp.api.estoque.service.TipoLocalEstoqueService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class LocalEstoqueMapper extends GenericMapper<LocalEstoque, LocalEstoqueDTO> {

	TipoLocalEstoqueService tipoLocalEstoqueService;
	EmpresaFilialService empresaFilialService;
	
	public LocalEstoqueMapper(LocalEstoqueRepository repository,
			TipoLocalEstoqueService tipoLocalEstoqueService,
			EmpresaFilialService empresaFilialService) {
		super(repository, LocalEstoque::new, LocalEstoqueDTO::new);
		this.tipoLocalEstoqueService = tipoLocalEstoqueService;
		this.empresaFilialService = empresaFilialService;
	}

	@Override
	protected void setValuesToEntity(LocalEstoqueDTO dto, LocalEstoque entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setFilialEspecifica(dto.isFilialEspecifica());
		entity.setTipoLocalEstoque(tipoLocalEstoqueService.getById(dto.getTipoLocalEstoqueId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado Tipo de Local do Estoque com id " + dto.getTipoLocalEstoqueId())));
		if (dto.getEmpresaFilialId() != null) {
			entity.setEmpresaFilial(empresaFilialService.getById(dto.getEmpresaFilialId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrada a Filial com id " + dto.getEmpresaFilialId())));	
		}
	}

	@Override
	protected void setValuesToDto(LocalEstoque entity, LocalEstoqueDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setFilialEspecifica(entity.isFilialEspecifica());
		dto.setTipoLocalEstoqueId(entity.getTipoLocalEstoque().getId());
		dto.setTipoLocalEstoqueCodigo(entity.getTipoLocalEstoque().getCodigo());
		dto.setTipoLocalEstoqueNome(entity.getTipoLocalEstoque().getNome());
		if (entity.getEmpresaFilial() != null) {
			dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
			dto.setEmpresaFilialSigla(entity.getEmpresaFilial().getSigla());
			dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		}
	}
}
