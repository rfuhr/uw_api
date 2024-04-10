package br.com.ultraworks.erp.api.tabela.repository;

import java.util.List;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.persistence.QueryHint;

@Repository
public interface OperacaoInternaFiscalRepository extends UWRepository<OperacaoInternaFiscal, Long> {

	@QueryHints(value = {@QueryHint(name = "javax.persistence.cache.retrieveMode", value = "BYPASS")})
	List<OperacaoInternaFiscal> findByOperacaoInternaId(Long operacaoInternaId);
}
