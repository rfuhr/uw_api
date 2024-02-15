package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.origem.Origem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OrigemRepository extends UWRepository<Origem, Long> {

}
