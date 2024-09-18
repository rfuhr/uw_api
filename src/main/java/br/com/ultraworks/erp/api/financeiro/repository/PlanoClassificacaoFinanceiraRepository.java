package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceira;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.transaction.Transactional;

@Repository
public interface PlanoClassificacaoFinanceiraRepository extends UWRepository<PlanoClassificacaoFinanceira, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE PlanoClassificacaoFinanceira p SET p.sintetica = :sintetica WHERE p.id = :id")
	void atualizarSinteticaPorId(Long id, boolean sintetica);

}
