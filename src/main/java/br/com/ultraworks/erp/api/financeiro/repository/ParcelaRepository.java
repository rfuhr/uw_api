package br.com.ultraworks.erp.api.financeiro.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParcelaRepository extends UWRepository<ParcelaFinanceiro, Long> {

	List<ParcelaFinanceiro> findByTituloIdOrderByNumParcelaAscSeqParcelaAsc(Long tituloId);
}
