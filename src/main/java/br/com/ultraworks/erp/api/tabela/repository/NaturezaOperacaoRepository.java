package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface NaturezaOperacaoRepository extends UWRepository<NaturezaOperacao, Long> {

}
