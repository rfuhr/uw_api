package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissaoDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioPermissaoMapper;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioPermissaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UsuarioPermissaoService extends GenericService<UsuarioPermissao, Long, UsuarioPermissaoDTO> {

	private UsuarioPermissaoRepository repository;

	@Autowired
	public UsuarioPermissaoService(UsuarioPermissaoRepository repository, UsuarioPermissaoMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public void salvarPermissoes(List<UsuarioPermissao> listaUsuarioPermissao) {
		List<Long> listIdsUsuarioPermissao = listaUsuarioPermissao.stream()
				.filter(el -> el.getId() != null && el.getId() > 0).map(usuarioPermissao -> usuarioPermissao.getId())
				.collect(Collectors.toList());
		repository.deleteByIdNotIn(listIdsUsuarioPermissao);
		repository.saveAll(listaUsuarioPermissao);
	}
	
	public void deletePermissoesByUserId(Long id) {
		repository.deleteByUsuarioId(id);
	}

	public List<UsuarioPermissao> findByUsuarioId(Long id) {
		return repository.findByUsuarioId(id);
		
	}
}
