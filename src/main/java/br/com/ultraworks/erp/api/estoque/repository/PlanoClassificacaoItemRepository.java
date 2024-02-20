package br.com.ultraworks.erp.api.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItem;
import br.com.ultraworks.erp.api.seguranca.domain.perfilFuncionalidade.PerfilFuncionalidade;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.transaction.Transactional;

@Repository
public interface PlanoClassificacaoItemRepository extends UWRepository<PlanoClassificacaoItem, Long> {

	@Transactional
    @Modifying
    @Query("UPDATE PlanoClassificacaoItem p SET p.sintetica = :sintetica WHERE p.id = :id")
    void atualizarSinteticaPorId(Long id, boolean sintetica);
	
}
