package br.com.ultraworks.erp.api.seguranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.contexto.Contexto;
import jakarta.transaction.Transactional;

@Repository
public interface ContextoRepository extends JpaRepository<Contexto, Long>{

	Optional<Contexto> findFirstByUsuarioId(Long usuarioId);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Contexto e WHERE e.usuarioId = :id")
    void deleteByUsuarioId(@Param("id") Long id);
}
