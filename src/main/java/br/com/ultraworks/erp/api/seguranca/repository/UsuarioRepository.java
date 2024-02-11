package br.com.ultraworks.erp.api.seguranca.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface UsuarioRepository extends UWRepository<Usuario, Long> {

	Optional<Usuario> findByUserId(Long userId);

}
