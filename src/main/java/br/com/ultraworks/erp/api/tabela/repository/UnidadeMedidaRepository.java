package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedida;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface UnidadeMedidaRepository extends UWRepository<UnidadeMedida, Long> {

}
