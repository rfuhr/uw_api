package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivil;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface EstadoCivilRepository extends UWRepository<EstadoCivil, Long> {

}
