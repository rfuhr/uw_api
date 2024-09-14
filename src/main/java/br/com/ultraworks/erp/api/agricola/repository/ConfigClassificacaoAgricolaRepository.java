package br.com.ultraworks.erp.api.agricola.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigClassificacaoAgricolaRepository extends UWRepository<ConfigClassificacaoAgricola, Long> {

	@Query("SELECT c FROM ConfigClassificacaoAgricola c WHERE c.tipoClassificacaoAgricola.id = :tipoClassificacaoAgricolaId "
			+ " and c.item.id = :produtoId AND c.safra.id = :safraId"
			+ " and :valor between c.faixaInicial and c.faixaFinal")
	ConfigClassificacaoAgricola findByClassificacaoAgricola(Long tipoClassificacaoAgricolaId, Long produtoId,
			Long safraId, BigDecimal valor);
}
