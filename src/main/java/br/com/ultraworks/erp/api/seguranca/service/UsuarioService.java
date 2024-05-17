package br.com.ultraworks.erp.api.seguranca.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.UsuarioDTO;
import br.com.ultraworks.erp.api.seguranca.domain.vo.OrganogramaUsuarioVO;
import br.com.ultraworks.erp.api.seguranca.domain.vo.PermissaoAutonomiaUsuarioVO;
import br.com.ultraworks.erp.api.seguranca.domain.vo.PermissaoFuncionalidadeUsuarioVO;
import br.com.ultraworks.erp.api.seguranca.mapper.UsuarioMapper;
import br.com.ultraworks.erp.api.seguranca.repository.ContextoRepository;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioFuncionalidadeRepository;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioPermissaoRepository;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioRepository;
import br.com.ultraworks.erp.api.seguranca.repository.query.BuscaOrganogramaUsuarioQuery;
import br.com.ultraworks.erp.api.seguranca.repository.query.BuscaPermissaoAutonomiaUsuarioQuery;
import br.com.ultraworks.erp.api.seguranca.repository.query.BuscaPermissaoFuncionalidadeUsuarioQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.security.domain.user.User;
import br.com.ultraworks.erp.core.security.repository.UserRepository;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UsuarioService extends GenericService<Usuario, Long, UsuarioDTO> {

	private UsuarioRepository usuarioRepository;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private BuscaOrganogramaUsuarioQuery query;
	private UsuarioMapper usuarioMapper;
	private UsuarioPermissaoRepository usuarioPermissaoRepository;
	private UsuarioFuncionalidadeRepository usuarioFuncionalidadeRepository;
	private BuscaPermissaoFuncionalidadeUsuarioQuery queryBuscaPermissaoFuncionalidadeUsuarioQuery;
	private BuscaPermissaoAutonomiaUsuarioQuery queryBuscaPermissaoAutonomiaUsuarioQuery;
	private ContextoRepository contextoRepository;

	@Autowired
	public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper, PasswordEncoder passwordEncoder,
			BuscaOrganogramaUsuarioQuery query, UserRepository userRepository,
			UsuarioPermissaoRepository usuarioPermissaoRepository,
			UsuarioFuncionalidadeRepository usuarioFuncionalidadeRepository, ContextoRepository contextoRepository,
			BuscaPermissaoFuncionalidadeUsuarioQuery queryBuscaPermissaoFuncionalidadeUsuarioQuery,
			BuscaPermissaoAutonomiaUsuarioQuery queryBuscaPermissaoAutonomiaUsuarioQuery) {
		super(repository, mapper);
		this.usuarioRepository = repository;
		this.passwordEncoder = passwordEncoder;
		this.query = query;
		this.userRepository = userRepository;
		this.usuarioPermissaoRepository = usuarioPermissaoRepository;
		this.usuarioFuncionalidadeRepository = usuarioFuncionalidadeRepository;
		this.contextoRepository = contextoRepository;
		this.queryBuscaPermissaoFuncionalidadeUsuarioQuery = queryBuscaPermissaoFuncionalidadeUsuarioQuery;
		this.queryBuscaPermissaoAutonomiaUsuarioQuery = queryBuscaPermissaoAutonomiaUsuarioQuery;
	}

	public Usuario novoUsuario(Usuario usuario, String username, String password) {
		if (usuario.getId() != null) {
			throw new BusinessException("Usuário a ser criado não pode ter um ID");
		}
		String passwordCriptografado = this.passwordEncoder.encode(password);
		User user = userRepository.save(new User(username, passwordCriptografado));
		usuario.setUserId(user.getId().longValue());
		return this.usuarioRepository.save(usuario);
	}

	public Usuario alterarUsuario(Usuario usuario, String username, String password) {
		if (usuario.getId() == null) {
			throw new BusinessException("Usuário a ser alterado deve ter um ID");
		}
		User user = userRepository.findById(BigDecimal.valueOf(usuario.getUserId())).get();
		user.setUsername(username);
		if (StringUtils.isNotBlank(password)) {
			String passwordCriptografado = this.passwordEncoder.encode(password);
			user.setPassword(passwordCriptografado);
		}
		userRepository.save(user);
		usuario.setUserId(user.getId().longValue());
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		userRepository.findById(new BigDecimal(id)).orElseThrow(RegisterNotFoundException::new);
		usuarioFuncionalidadeRepository.deleteByUsuarioId(id);
		usuarioPermissaoRepository.deleteByUsuarioId(id);
		contextoRepository.deleteByUsuarioId(id);
		userRepository.deleteById(new BigDecimal(id));
		usuarioRepository.deleteById(id);
	}

	public Optional<Usuario> findByUserId(long userId) {
		return this.usuarioRepository.findByUserId(userId);
	}

	public List<OrganogramaUsuarioVO> getOrganogramaUsuario(long usuarioId) {
		return this.query.executeSQL(usuarioId);
	}

	public Optional checkPermissao(long empresaId, long empresaFilialId, String tag, String operacao) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = this.findByUserId(user.getId().longValue())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado usuário com user id" + user.getId()));
		Optional<Boolean> retorno = Optional.of(Boolean.FALSE);

		if (usuario.isAdmin()) {
			List<OrganogramaUsuarioVO> organograma = getOrganogramaUsuario(usuario.getId());
			retorno = Optional.of(organograma.stream()
					.anyMatch(el -> el.getEmpresaId() == empresaId && el.getEmpresaFilialId() == empresaFilialId));
		} else {

			List<PermissaoFuncionalidadeUsuarioVO> permissoes = this.queryBuscaPermissaoFuncionalidadeUsuarioQuery
					.executeSQL(empresaId, empresaFilialId, usuario.getId(), tag);
			if (permissoes != null && !permissoes.isEmpty()) {
				PermissaoFuncionalidadeUsuarioVO permissao = permissoes.get(0);
				if (!permissao.isCrud()) {
					retorno = Optional.of(permissao.isLiberado());
				} else {
					switch (operacao) {
					case "consultar":
						retorno = Optional.of(permissao.isConsultar());
						break;
					case "inserir":
						retorno = Optional.of(permissao.isInserir());
						break;
					case "alterar":
						retorno = Optional.of(permissao.isAlterar());
						break;
					case "excluir":
						retorno = Optional.of(permissao.isExcluir());
						break;

					default:
						break;
					}
				}
			}
		}
		return retorno;
	}

	public Optional checkAutonomia(long empresaId, long empresaFilialId, String tag) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = this.findByUserId(user.getId().longValue())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado usuário com user id" + user.getId()));
		Optional<Boolean> retorno = Optional.of(Boolean.FALSE);

		if (usuario.isAdmin()) {
			List<OrganogramaUsuarioVO> organograma = getOrganogramaUsuario(usuario.getId());
			retorno = Optional.of(organograma.stream()
					.anyMatch(el -> el.getEmpresaId() == empresaId && el.getEmpresaFilialId() == empresaFilialId));
		} else {

			List<PermissaoAutonomiaUsuarioVO> permissoes = this.queryBuscaPermissaoAutonomiaUsuarioQuery
					.executeSQL(empresaId, empresaFilialId, usuario.getId(), tag);
			if (permissoes != null && !permissoes.isEmpty()) {
				PermissaoAutonomiaUsuarioVO permissao = permissoes.get(0);
				return Optional.of(true);
			}
		}
		return retorno;
	}
}
