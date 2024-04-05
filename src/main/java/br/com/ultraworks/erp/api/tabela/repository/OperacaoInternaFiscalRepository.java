package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoInternaFiscalRepository extends UWRepository<OperacaoInternaFiscal, Long> {

	OperacaoInternaFiscal findByOperacaoInternaId(Long operacaoInternaId);
}
