package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface RegraAtividadeRepository extends UWRepository<RegraAtividade, Long> {

	@Query(value = "SELECT proximo_codigo('regra_atividade', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
