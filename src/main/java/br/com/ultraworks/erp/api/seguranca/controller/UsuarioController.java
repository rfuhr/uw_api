package br.com.ultraworks.erp.api.seguranca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.usuario.InfoUserViewDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.UsuarioDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissaoDTO;
import br.com.ultraworks.erp.api.seguranca.domain.vo.OrganogramaUsuarioVO;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioFuncionalidadeMapper;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioMapper;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioPermissaoMapper;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioFuncionalidadeService;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioPermissaoService;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;
import br.com.ultraworks.erp.core.security.domain.user.User;
import br.com.ultraworks.erp.core.util.ResponseUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/seguranca/usuario")
public class UsuarioController extends GenericController<Usuario, Long, UsuarioDTO> {

	private UsuarioService usuarioService;
	private UsuarioMapper usuarioMapper;
	private UsuarioPermissaoMapper usuarioPermissaoMapper;
	private UsuarioPermissaoService usuarioPermissaoService;
	private UsuarioFuncionalidadeMapper usuarioFuncionalidadeMapper;
	private UsuarioFuncionalidadeService usuarioFuncionalidadeService;

	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper,
			UsuarioPermissaoMapper usuarioPermissaoMapper, UsuarioPermissaoService usuarioPermissaoService,
			UsuarioFuncionalidadeMapper usuarioFuncionalidadeMapper,
			UsuarioFuncionalidadeService usuarioFuncionalidadeService) {
		super(usuarioService, usuarioMapper);
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
		this.usuarioPermissaoMapper = usuarioPermissaoMapper;
		this.usuarioPermissaoService = usuarioPermissaoService;
		this.usuarioFuncionalidadeMapper = usuarioFuncionalidadeMapper;
		this.usuarioFuncionalidadeService = usuarioFuncionalidadeService;
	}

	@PostMapping
	@Transactional
	@Override
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO newUsuarioRequest) {
		Usuario novoUsuario = this.usuarioMapper.toNewEntity(newUsuarioRequest);
		Usuario usuario = this.usuarioService.novoUsuario(novoUsuario, newUsuarioRequest.getUsername(),
				newUsuarioRequest.getSenha());
		List<UsuarioPermissaoDTO> permissoesDTO = newUsuarioRequest.getPermissoes();
		if (permissoesDTO != null && !permissoesDTO.isEmpty())
			permissoesDTO.forEach(el -> el.setUsuarioId(usuario.getId()));

		List<UsuarioPermissao> listaUsuarioPermissao = this.usuarioPermissaoMapper
				.toNewEntity(newUsuarioRequest.getPermissoes());
		this.usuarioPermissaoService.salvarPermissoes(listaUsuarioPermissao);

		List<UsuarioFuncionalidadeDTO> funcionalidadesDTO = newUsuarioRequest.getFuncionalidades();
		if (funcionalidadesDTO != null && !funcionalidadesDTO.isEmpty())
			funcionalidadesDTO.forEach(el -> el.setUsuarioId(usuario.getId()));

		List<UsuarioFuncionalidade> listaUsuarioFuncionalidade = this.usuarioFuncionalidadeMapper
				.toNewEntity(newUsuarioRequest.getFuncionalidades());
		this.usuarioFuncionalidadeService.salvarPermissoes(listaUsuarioFuncionalidade);

		return ResponseEntity.ok(usuarioMapper.toDto(usuario));
	}

	@PutMapping
	@Transactional
	@Override
	public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody UsuarioDTO newUsuarioRequest) {
		Usuario usuarioAlterar = this.usuarioMapper.toUpdateEntity(newUsuarioRequest);

		Usuario usuario = this.usuarioService.alterarUsuario(usuarioAlterar, newUsuarioRequest.getUsername(),
				newUsuarioRequest.getSenha());
		List<UsuarioPermissaoDTO> permissoesDTO = newUsuarioRequest.getPermissoes();
		if (permissoesDTO != null && !permissoesDTO.isEmpty())
			permissoesDTO.forEach(el -> el.setUsuarioId(usuario.getId()));

		List<UsuarioPermissao> listaUsuarioPermissao = this.usuarioPermissaoMapper
				.toEntity(newUsuarioRequest.getPermissoes());
		this.usuarioPermissaoService.salvarPermissoes(listaUsuarioPermissao);

		List<UsuarioFuncionalidadeDTO> funcionalidadesDTO = newUsuarioRequest.getFuncionalidades();
		if (funcionalidadesDTO != null && !funcionalidadesDTO.isEmpty())
			funcionalidadesDTO.forEach(el -> el.setUsuarioId(usuario.getId()));

		List<UsuarioFuncionalidade> listaUsuarioFuncionalidade = this.usuarioFuncionalidadeMapper
				.toEntity(newUsuarioRequest.getFuncionalidades());
		this.usuarioFuncionalidadeService.salvarPermissoes(listaUsuarioFuncionalidade);
		return ResponseEntity.ok(usuarioMapper.toDto(usuario));
	}

	@GetMapping("/info")
	public ResponseEntity<InfoUserViewDTO> getInfoUser(@AuthenticationPrincipal User user) {
		Usuario usuario = usuarioService.findByUserId(user.getId().longValue())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado usuário"));
		Optional<InfoUserViewDTO> infoUser = Optional
				.of(new InfoUserViewDTO(usuario.getNome(), user.getUsername(), usuario.getEmail()));
		return ResponseUtil.wrapOrNotFound(infoUser);
	}

	@GetMapping("{usuarioId}/organograma")
	public ResponseEntity<?> getOrganogramaUsuario(@PathVariable("usuarioId") long usuarioId) {
		List<OrganogramaUsuarioVO> all = usuarioService.getOrganogramaUsuario(usuarioId);
		return ResponseEntity.ok(all);
	}

	@GetMapping("{id}")
	@Override
	public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
		Optional<Usuario> entity = usuarioService.getById(id);
		if (entity.isPresent()) {
			UsuarioDTO dto = mapper.toDto(entity.get());
			List<UsuarioPermissao> listaUsuarioPermissao = usuarioPermissaoService
					.findByUsuarioId(entity.get().getId());
			if (listaUsuarioPermissao != null && !listaUsuarioPermissao.isEmpty()) {
				List<UsuarioPermissaoDTO> dtoPermissoes = usuarioPermissaoMapper.toDto(listaUsuarioPermissao);
				dto.setPermissoes(dtoPermissoes);
			}
			List<UsuarioFuncionalidade> listaUsuarioFuncionalidade = usuarioFuncionalidadeService
					.findByUsuarioId(entity.get().getId());
			if (listaUsuarioFuncionalidade != null && !listaUsuarioFuncionalidade.isEmpty()) {
				List<UsuarioFuncionalidadeDTO> dtoPermissoes = usuarioFuncionalidadeMapper
						.toDto(listaUsuarioFuncionalidade);
				dto.setFuncionalidades(dtoPermissoes);
			}
			return ResponseEntity.ok(dto);
		} else {
			throw new RegisterNotFoundException("Não encontrado registro");
		}
	};

	@GetMapping("/permissao/{empresaId}/{empresaFilialId}/{tag}")
	public ResponseEntity<?> findPermissao(@PathVariable("empresaId") long empresaId,
			@PathVariable("empresaFilialId") long empresaFilialId, @PathVariable("tag") String tag, @PathParam("operacao") String operacao) {
			Optional checkPermissao = usuarioService.checkPermissao(empresaId, empresaFilialId, tag, operacao);
			if (checkPermissao.isPresent() && checkPermissao.get() == Boolean.TRUE) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); 
			}
	}
}
