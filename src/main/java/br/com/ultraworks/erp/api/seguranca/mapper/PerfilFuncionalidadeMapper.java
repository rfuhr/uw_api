package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.repository.PerfilFuncionalidadeRepository;
import br.com.ultraworks.erp.api.seguranca.repository.PerfilRepository;
import br.com.ultraworks.erp.api.seguranca.service.FuncionalidadeService;
import br.com.ultraworks.erp.api.seguranca.service.PerfilService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PerfilFuncionalidadeMapper extends GenericMapper<PerfilFuncionalidade, PerfilFuncionalidadeDTO> {

	PerfilRepository perfilRepository;
	FuncionalidadeService funcionalidadeService;

	public PerfilFuncionalidadeMapper(PerfilFuncionalidadeRepository perfilFuncionalidadeRepository,
			PerfilRepository perfilRepository, FuncionalidadeService funcionalidadeService) {
		super(perfilFuncionalidadeRepository, PerfilFuncionalidade::new, PerfilFuncionalidadeDTO::new);
		this.perfilRepository = perfilRepository;
		this.funcionalidadeService = funcionalidadeService;
	}

	@Override
	protected void setValuesToEntity(PerfilFuncionalidadeDTO dto, PerfilFuncionalidade entity) {
		entity.setId(dto.getId() == null || dto.getId() < 0 ? null : dto.getId());
		entity.setPerfil(perfilRepository.findById(dto.getPerfilId()).orElseThrow(() -> new RegisterNotFoundException("Não encontrado perfil com id " + dto.getPerfilId())));
		entity.setFuncionalidade(funcionalidadeService.getById(dto.getFuncionalidadeId()).orElseThrow(() -> new RegisterNotFoundException("Não encontrado funcionalidade com id " + dto.getFuncionalidadeId())));
		entity.setConsultar(dto.isConsultar());
		entity.setInserir(dto.isInserir());
		entity.setExcluir(dto.isExcluir());
		entity.setAlterar(dto.isAlterar());
	}

	@Override
	protected void setValuesToDto(PerfilFuncionalidade entity, PerfilFuncionalidadeDTO dto) {
		dto.setId(entity.getId());
		dto.setPerfilId(entity.getPerfil().getId());
		dto.setFuncionalidadeId(entity.getFuncionalidade().getId());
		dto.setConsultar(entity.isConsultar());
		dto.setInserir(entity.isInserir());
		dto.setExcluir(entity.isExcluir());
		dto.setAlterar(entity.isAlterar());
	}
}
