package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoContabilRepository extends UWRepository<GrupoContabil, Long> {

}
