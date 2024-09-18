package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoClassificacaoAgricolaRepository extends UWRepository<TipoClassificacaoAgricola, Long> {

	@Query("SELECT DISTINCT c.tipoClassificacaoAgricola FROM ConfigClassificacaoAgricola c WHERE c.item.id = :produtoId AND c.safra.id = :safraId")
	List<TipoClassificacaoAgricola> findByTiposConfigurados(Long produtoId, Long safraId);
}
