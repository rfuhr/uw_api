package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoTributacaoRepository extends UWRepository<GrupoTributacao, Long> {

}
