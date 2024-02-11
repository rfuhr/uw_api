package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.api.seguranca.mapper.PerfilFuncionalidadeMapper;
import br.com.ultraworks.erp.api.seguranca.repository.PerfilFuncionalidadeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PerfilFuncionalidadeService extends GenericService<PerfilFuncionalidade, Long, PerfilFuncionalidadeDTO> {

	private PerfilFuncionalidadeRepository repository;
	private PerfilFuncionalidadeMapper mapper;

	@Autowired
	public PerfilFuncionalidadeService(PerfilFuncionalidadeRepository repository, PerfilFuncionalidadeMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public void salvarPermissoes(long perfilId, List<PerfilFuncionalidadeDTO> perfilFuncionalidadeDTOs) {
		if (perfilFuncionalidadeDTOs != null && !perfilFuncionalidadeDTOs.isEmpty()) {
			List<PerfilFuncionalidade> listaPerfilFuncionalidade = this.mapper
					.toEntity(perfilFuncionalidadeDTOs);
		
			List<Long> listIdsPerfilFuncionalidades = perfilFuncionalidadeDTOs.stream()
				.filter(el -> el.getId() != null && el.getId() > 0).map(perfilFuncionalidade -> perfilFuncionalidade.getId())
				.collect(Collectors.toList());
			repository.deleteByIdNotIn(listIdsPerfilFuncionalidades);
			repository.saveAll(listaPerfilFuncionalidade);
		} else {
			repository.deleteByPerfilId(perfilId);
		}
		
	}
	
	public void deletePermissoesByPerfilId(Long id) {
		repository.deleteByPerfilId(id);
	}

	public List<PerfilFuncionalidade> findByPerfilId(Long id) {
		return repository.findByPerfilId(id);
		
	}
}
