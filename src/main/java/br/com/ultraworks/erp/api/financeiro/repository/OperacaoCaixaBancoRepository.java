package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoCaixaBancoRepository extends UWRepository<OperacaoCaixaBanco, Long> {

	@Query(value = "SELECT proximo_codigo('operacao_cxbco', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
