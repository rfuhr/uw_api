package br.com.ultraworks.erp.api.seguranca.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.autonomia.Autonomia;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface AutonomiaRepository extends UWRepository<Autonomia, Long> {

}
