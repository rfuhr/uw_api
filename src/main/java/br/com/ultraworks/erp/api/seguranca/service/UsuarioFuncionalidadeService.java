package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioFuncionalidadeMapper;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioFuncionalidadeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UsuarioFuncionalidadeService extends GenericService<UsuarioFuncionalidade, Long, UsuarioFuncionalidadeDTO> {

	private UsuarioFuncionalidadeRepository repository;

	@Autowired
	public UsuarioFuncionalidadeService(UsuarioFuncionalidadeRepository repository, UsuarioFuncionalidadeMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public void salvarPermissoes(List<UsuarioFuncionalidade> listaUsuarioFuncionalidade) {
		List<Long> listIdsUsuarioFuncionalidade = listaUsuarioFuncionalidade.stream()
				.filter(el -> el.getId() != null && el.getId() > 0).map(usuarioFuncionalidade -> usuarioFuncionalidade.getId())
				.collect(Collectors.toList());
		repository.deleteByIdNotIn(listIdsUsuarioFuncionalidade);
		repository.saveAll(listaUsuarioFuncionalidade);
	}
	
	public void deletePermissoesByUserId(Long id) {
		repository.deleteByUsuarioId(id);
	}

	public List<UsuarioFuncionalidade> findByUsuarioId(Long id) {
		return repository.findByUsuarioId(id);
		
	}
}

