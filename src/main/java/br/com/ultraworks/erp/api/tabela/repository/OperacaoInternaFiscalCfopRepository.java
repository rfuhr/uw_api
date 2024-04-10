package br.com.ultraworks.erp.api.tabela.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfop;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoInternaFiscalCfopRepository extends UWRepository<OperacaoInternaFiscalCfop, Long> {

	List<OperacaoInternaFiscalCfop> findByOperacaoInternaFiscalId(Long operacaoInternaFiscalId);
	
}
