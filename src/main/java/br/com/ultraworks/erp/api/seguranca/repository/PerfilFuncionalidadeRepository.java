package br.com.ultraworks.erp.api.seguranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.transaction.Transactional;

@Repository
public interface PerfilFuncionalidadeRepository extends UWRepository<PerfilFuncionalidade, Long> {

	@Transactional
    @Modifying
    @Query("DELETE FROM PerfilFuncionalidade e WHERE e.id NOT IN :ids")
    void deleteByIdNotIn(@Param("ids") List<Long> ids);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM PerfilFuncionalidade e WHERE e.perfil.id = :id")
    void deleteByPerfilId(@Param("id") Long id);
	
	List<PerfilFuncionalidade> findByPerfilId(Long perfilId);
}
