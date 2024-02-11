package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CidadeRepository extends UWRepository<Cidade, Long> {
	
}
