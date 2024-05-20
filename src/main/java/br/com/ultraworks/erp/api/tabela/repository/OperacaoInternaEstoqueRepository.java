package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.persistence.QueryHint;

@Repository
public interface OperacaoInternaEstoqueRepository extends UWRepository<OperacaoInternaEstoque, Long> {

	@QueryHints(value = {@QueryHint(name = "javax.persistence.cache.retrieveMode", value = "BYPASS")})
	OperacaoInternaEstoque findByOperacaoInternaId(Long operacaoInternaId);
}
