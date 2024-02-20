package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SituacaoTributariaRepository extends UWRepository<SituacaoTributaria, Long> {

}
