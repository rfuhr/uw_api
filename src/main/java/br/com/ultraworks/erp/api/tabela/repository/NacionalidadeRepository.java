package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.Nacionalidade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface NacionalidadeRepository extends UWRepository<Nacionalidade, Long> {

}
