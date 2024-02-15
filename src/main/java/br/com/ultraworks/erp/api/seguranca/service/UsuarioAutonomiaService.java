package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioAutonomia.UsuarioAutonomia;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioAutonomia.UsuarioAutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioAutonomiaMapper;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioAutonomiaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UsuarioAutonomiaService extends GenericService<UsuarioAutonomia, Long, UsuarioAutonomiaDTO> {

	private UsuarioAutonomiaRepository repository;

	@Autowired
	public UsuarioAutonomiaService(UsuarioAutonomiaRepository repository, UsuarioAutonomiaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public void salvarPermissoes(List<UsuarioAutonomia> listaUsuarioAutonomia) {
		List<Long> listIdsUsuarioAutonomia = listaUsuarioAutonomia.stream()
				.filter(el -> el.getId() != null && el.getId() > 0).map(usuarioAutonomia -> usuarioAutonomia.getId())
				.collect(Collectors.toList());
		repository.deleteByIdNotIn(listIdsUsuarioAutonomia);
		repository.saveAll(listaUsuarioAutonomia);
	}

	public void deletePermissoesByUserId(Long id) {
		repository.deleteByUsuarioId(id);
	}

	public List<UsuarioAutonomia> findByUsuarioId(Long id) {
		return repository.findByUsuarioId(id);

	}
}
