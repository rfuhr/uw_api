package br.com.ultraworks.erp.api.seguranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioAutonomia.UsuarioAutonomia;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioAutonomiaRepository extends UWRepository<UsuarioAutonomia, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM UsuarioAutonomia e WHERE e.id NOT IN :ids")
	void deleteByIdNotIn(@Param("ids") List<Long> ids);

	@Transactional
	@Modifying
	@Query("DELETE FROM UsuarioAutonomia e WHERE e.usuario.id = :id")
	void deleteByUsuarioId(@Param("id") Long id);

	List<UsuarioAutonomia> findByUsuarioId(Long usuarioId);
}
