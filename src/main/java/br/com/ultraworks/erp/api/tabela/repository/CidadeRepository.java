package br.com.ultraworks.erp.api.tabela.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CidadeRepository extends UWRepository<Cidade, Long> {
	
	Optional<Cidade> findByCodigoIBGE(Long ibge);
}
