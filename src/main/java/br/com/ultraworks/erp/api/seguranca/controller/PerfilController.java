package br.com.ultraworks.erp.api.seguranca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.perfil.Perfil;
import br.com.ultraworks.erp.api.seguranca.domain.perfil.PerfilDTO;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.UsuarioDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissaoDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.PerfilMapper;
import br.com.ultraworks.erp.api.seguranca.service.PerfilService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;
import br.com.ultraworks.erp.core.util.ResponseUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/seguranca/perfil")
public class PerfilController extends GenericController<Perfil, Long, PerfilDTO> {

	private PerfilService service;
	private PerfilMapper mapper;

	public PerfilController(PerfilService service, PerfilMapper mapper) {
		super(service, mapper);
		this.service = service;
		this.mapper = mapper;
	}
	
	@Override
	@PostMapping
	@Transactional
	public ResponseEntity<PerfilDTO> create(@RequestBody PerfilDTO dto) {
		Perfil entity = this.service.novoPerfil(dto);
		return ResponseUtil.wrapOrNotFound(Optional.of(mapper.toDto(entity)));
	}
	
	@PutMapping
	@Transactional
	@Override
	public ResponseEntity<PerfilDTO> update(@Valid @RequestBody PerfilDTO dto) {
		Perfil entity = this.service.alterarPerfil(dto);
		return ResponseEntity
				.ok(this.mapper.toDto(entity));
	}
	
	@GetMapping("{id}")
	@Override
	public ResponseEntity<PerfilDTO> getById(@PathVariable Long id) {
		PerfilDTO dto = this.service.buscarPeloId(id);
		return ResponseEntity.ok(dto);
	};
}