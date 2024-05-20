package br.com.ultraworks.erp.api.tabela.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;
import br.com.ultraworks.erp.api.estoque.service.GrupoContabilService;
import br.com.ultraworks.erp.api.estoque.service.LocalEstoqueService;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoqueDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaEstoqueRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaEstoqueMapper extends GenericMapper<OperacaoInternaEstoque, OperacaoInternaEstoqueDTO> {

	LocalEstoqueService localEstoqueService;
	GrupoContabilService grupoContabilService;
	
	public OperacaoInternaEstoqueMapper(OperacaoInternaEstoqueRepository repository,
			LocalEstoqueService localEstoqueService,
			GrupoContabilService grupoContabilService) {
		super(repository, OperacaoInternaEstoque::new, OperacaoInternaEstoqueDTO::new);
		this.localEstoqueService = localEstoqueService;
		this.grupoContabilService = grupoContabilService;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaEstoqueDTO dto, OperacaoInternaEstoque entity) {
		entity.setId(dto.getId());
		entity.setCalculaCustoMedio(dto.isCalculaCustoMedio());
		entity.setInformaGrupoContabil(dto.isInformaGrupoContabil());
		entity.setInformaLocalEstoque(dto.isInformaLocalEstoque());
		if (dto.getLocalEstoqueId() != null) {
			entity.setLocalEstoque(localEstoqueService.getById(dto.getLocalEstoqueId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado o Local de Estoque com id " + dto.getLocalEstoqueId())));	
		} else {
			entity.setLocalEstoque(null);
		}
		if (dto.getGrupoContabilId() != null) {
			entity.setGrupoContabil(grupoContabilService.getById(dto.getGrupoContabilId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado o Grupo Contábil com id " + dto.getGrupoContabilId())));	
		} else {
			entity.setGrupoContabil(null);
		}
		if (StringUtils.isNotBlank(dto.getOperacaoEstoqueFisico()))
			entity.setOperacaoEstoqueFisico(OperacaoEstoque.fromValue(dto.getOperacaoEstoqueFisico()));
		if (StringUtils.isNotBlank(dto.getOperacaoEstoqueFinanceiro()))
			entity.setOperacaoEstoqueFinanceiro(OperacaoEstoque.fromValue(dto.getOperacaoEstoqueFinanceiro()));

	}

	@Override
	protected void setValuesToDto(OperacaoInternaEstoque entity, OperacaoInternaEstoqueDTO dto) {
		dto.setId(entity.getId());
		dto.setCalculaCustoMedio(entity.isCalculaCustoMedio());
		dto.setInformaGrupoContabil(entity.isInformaGrupoContabil());
		dto.setInformaLocalEstoque(entity.isInformaLocalEstoque());
		if (entity.getOperacaoEstoqueFisico() != null)
			dto.setOperacaoEstoqueFisico(entity.getOperacaoEstoqueFisico().getValue());
		if (entity.getOperacaoEstoqueFinanceiro() != null)
			dto.setOperacaoEstoqueFinanceiro(entity.getOperacaoEstoqueFinanceiro().getValue());
		if (entity.getLocalEstoque() != null) {
			dto.setLocalEstoqueId(entity.getLocalEstoque().getId());
			dto.setLocalEstoqueCodigo(entity.getLocalEstoque().getCodigo());
			dto.setLocalEstoqueNome(entity.getLocalEstoque().getNome());
		}
		if (entity.getGrupoContabil() != null) {
			dto.setGrupoContabilId(entity.getGrupoContabil().getId());
			dto.setGrupoContabilCodigo(entity.getGrupoContabil().getCodigo());
			dto.setGrupoContabilNome(entity.getGrupoContabil().getNome());
		}
	}
}
