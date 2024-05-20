package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface HistoricoPadraoRepository extends UWRepository<HistoricoPadrao, Long> {

	@Query(value = "SELECT proximo_codigo('historico_padrao', 'codigo')", nativeQuery = true)
	int getProximoCodigo();	
}
