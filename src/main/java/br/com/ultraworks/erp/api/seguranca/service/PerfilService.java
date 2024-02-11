package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.perfil.Perfil;
import br.com.ultraworks.erp.api.seguranca.domain.perfil.PerfilDTO;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.PerfilFuncionalidadeMapper;
import br.com.ultraworks.erp.api.seguranca.mapper.PerfilMapper;
import br.com.ultraworks.erp.api.seguranca.repository.PerfilRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import jakarta.validation.Valid;

@Service
public class PerfilService extends GenericService<Perfil, Long, PerfilDTO> {

	PerfilRepository repository;
	PerfilMapper mapper;
	PerfilFuncionalidadeService perfilFuncionalidadeService;
	PerfilFuncionalidadeMapper perfilFuncionalidadeMapper;

	@Autowired
	public PerfilService(PerfilRepository repository, PerfilMapper mapper,
			PerfilFuncionalidadeService perfilFuncionalidadeService, PerfilFuncionalidadeMapper perfilFuncionalidadeMapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
		this.perfilFuncionalidadeService = perfilFuncionalidadeService;
		this.perfilFuncionalidadeMapper = perfilFuncionalidadeMapper;
	}

	public Perfil novoPerfil(PerfilDTO perfilDto) {
		Perfil novoPerfil = this.mapper.toNewEntity(perfilDto);
		Perfil novoPerfilSaved = this.repository.save(novoPerfil);

		List<PerfilFuncionalidadeDTO> perfilFuncionalidadeDTOs = perfilDto.getPerfisFuncionalidades();
		if (perfilFuncionalidadeDTOs != null && !perfilFuncionalidadeDTOs.isEmpty())
			perfilFuncionalidadeDTOs.forEach(el -> el.setPerfilId(novoPerfilSaved.getId()));

		this.perfilFuncionalidadeService.salvarPermissoes(novoPerfilSaved.getId(), perfilFuncionalidadeDTOs);

		return novoPerfil;
	}

	public Perfil alterarPerfil(@Valid PerfilDTO dto) {
		Perfil perfil = this.mapper.toUpdateEntity(dto);
		Perfil perfilSaved = this.repository.save(perfil);
		
		List<PerfilFuncionalidadeDTO> perfilFuncionalidadeDTOs = dto.getPerfisFuncionalidades();
		if (perfilFuncionalidadeDTOs != null && !perfilFuncionalidadeDTOs.isEmpty())
			perfilFuncionalidadeDTOs.forEach(el -> el.setPerfilId(perfilSaved.getId()));

		this.perfilFuncionalidadeService.salvarPermissoes(perfilSaved.getId(), perfilFuncionalidadeDTOs);
		
		return perfil;
	}

	public PerfilDTO buscarPeloId(Long id) {
		Optional<Perfil> entity = super.getById(id);
		if (entity.isPresent()) {
			PerfilDTO dto = mapper.toDto(entity.get());
			List<PerfilFuncionalidade> listaPerfilFuncionalidade = perfilFuncionalidadeService.findByPerfilId(entity.get().getId());
			if (listaPerfilFuncionalidade != null && !listaPerfilFuncionalidade.isEmpty()) {
				List<PerfilFuncionalidadeDTO> dtoPermissoes = perfilFuncionalidadeMapper.toDto(listaPerfilFuncionalidade);
				dto.setPerfisFuncionalidades(dtoPermissoes);
			}
			return dto;
		} else {
			throw  new RegisterNotFoundException("Não encontrado registro");
		}
		
	}
	

}
