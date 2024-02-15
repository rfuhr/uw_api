package br.com.ultraworks.erp.api.seguranca.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomia;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoAutonomiaRepository extends UWRepository<GrupoAutonomia, Long> {

}
