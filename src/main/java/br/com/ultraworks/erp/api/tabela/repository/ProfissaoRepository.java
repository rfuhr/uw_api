package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.profissao.Profissao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ProfissaoRepository extends UWRepository<Profissao, Long> {

}
