package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.contexto.Contexto;
import br.com.ultraworks.erp.api.seguranca.domain.contexto.ContextoDTO;
import br.com.ultraworks.erp.api.seguranca.domain.modulo.Modulo;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.domain.vo.OrganogramaUsuarioVO;
import br.com.ultraworks.erp.api.seguranca.mapper.ContextoMapper;
import br.com.ultraworks.erp.api.seguranca.repository.ContextoRepository;
import br.com.ultraworks.erp.api.seguranca.repository.query.BuscaOrganogramaUsuarioQuery;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContextoService {

	private static final String NAO_ENCONTRADO_REGISTRO_PARA_MODULO_INFORMADO = "Não encontrado registro para módulo informado";
	private static final String NAO_ENCONTRADO_REGISTRO_PARA_USUARIO_INFORMADO = "Não encontrado registro para usuário informado";
	private static final String NAO_ENCONTRADO_CONTEXTO_PARA_USUARIO_INFORMADO = "Não encontrado contexto para usuário informado";

	UsuarioService usuarioService;
	BuscaOrganogramaUsuarioQuery buscaOrganogramaUsuarioQuery;
	ContextoRepository contextoRepository;
	ModuloService moduloService;
	ContextoMapper contextoMapper;

	public ContextoDTO getContextoDTOByUsuario(long usuarioId) {
		Usuario usuario = usuarioService.getById(usuarioId)
				.orElseThrow(() -> new RegisterNotFoundException(NAO_ENCONTRADO_REGISTRO_PARA_USUARIO_INFORMADO));
		Contexto byUsuario = this.getByUsuario(usuario.getId());
		ContextoDTO contextoDTO = contextoMapper.toDto(byUsuario);
		Modulo modulo = moduloService.findById(contextoDTO.getModuloId())
				.orElseThrow(() -> new RegisterNotFoundException(NAO_ENCONTRADO_REGISTRO_PARA_MODULO_INFORMADO));
		contextoDTO.setModuloNome(modulo.getNome());
		contextoDTO.setModuloPathBase(modulo.getPathBase());
		return contextoDTO;
	}

	private Contexto getByUsuario(long usuarioId) {
		List<OrganogramaUsuarioVO> organograma = buscaOrganogramaUsuarioQuery.executeSQL(usuarioId);
		Optional<Contexto> firstResultOptional = contextoRepository.findFirstByUsuarioId(usuarioId);
		if (firstResultOptional.isPresent()) {
			Contexto contexto = firstResultOptional.get();
			Optional<OrganogramaUsuarioVO> findFirst = organograma.stream()
					.filter(el -> el.getEmpresaId() == contexto.getEmpresaId()
							&& el.getEmpresaFilialId() == contexto.getEmpresaFilialId())
					.findFirst();
			if (findFirst.isPresent()) {
				return contexto;
			} else {
				return alterarContextoPeloOrganograma(contexto, organograma);
			}
		} else {
			if (organograma != null && !organograma.isEmpty()) {
				return criarContextoPeloOrganograma(usuarioId, organograma);
			} else {
				throw new RegisterNotFoundException(NAO_ENCONTRADO_CONTEXTO_PARA_USUARIO_INFORMADO);
			}
		}
	}

	private Contexto criarContextoPeloOrganograma(Long usuarioId, List<OrganogramaUsuarioVO> organograma) {
		Contexto contexto = new Contexto();
		contexto.setEmpresaId(organograma.get(0).getEmpresaId());
		contexto.setEmpresaFilialId(organograma.get(0).getEmpresaFilialId());
		contexto.setUsuarioId(usuarioId);
		contexto.setModuloId(moduloService.getAll().get(0).getId());
		this.save(contexto);
		return contexto;
	}

	private Contexto alterarContextoPeloOrganograma(Contexto contexto, List<OrganogramaUsuarioVO> organograma) {
		contexto.setEmpresaId(organograma.get(0).getEmpresaId());
		contexto.setEmpresaFilialId(organograma.get(0).getEmpresaFilialId());
		contexto.setModuloId(moduloService.getAll().get(0).getId());
		this.save(contexto);
		return contexto;
	}

	public Optional<Contexto> findById(Long id) {
		return contextoRepository.findById(id);
	}

	public Contexto save(Contexto entity) {
		contextoRepository.saveAndFlush(entity);
		return entity;
	}

	public ContextoDTO alterarContexto(long usuarioId, ContextoDTO contextoDTO) {
		if (!usuarioService.getById(usuarioId).isPresent()) {
			throw new RegisterNotFoundException(NAO_ENCONTRADO_REGISTRO_PARA_USUARIO_INFORMADO);
		}
		Contexto contextoSaved = contextoMapper.toUpdateEntity(contextoDTO);
		Contexto contexto = contextoRepository.save(contextoSaved);
		contextoDTO = contextoMapper.toDto(contexto);
		Modulo modulo = moduloService.findById(contextoDTO.getModuloId())
				.orElseThrow(() -> new RegisterNotFoundException(NAO_ENCONTRADO_REGISTRO_PARA_MODULO_INFORMADO));
		contextoDTO.setModuloNome(modulo.getNome());
		contextoDTO.setModuloPathBase(modulo.getPathBase());
		return contextoDTO;
	}

}
