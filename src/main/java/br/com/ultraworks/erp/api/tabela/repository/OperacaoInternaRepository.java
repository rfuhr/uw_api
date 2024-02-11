package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoInternaRepository extends UWRepository<OperacaoInterna, Long> {

}
