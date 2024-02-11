package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.pais.Pais;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface PaisRepository extends UWRepository<Pais, Long> {
}
