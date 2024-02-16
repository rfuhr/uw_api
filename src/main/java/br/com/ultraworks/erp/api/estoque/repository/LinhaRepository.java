package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.linha.Linha;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface LinhaRepository extends UWRepository<Linha, Long> {

}
