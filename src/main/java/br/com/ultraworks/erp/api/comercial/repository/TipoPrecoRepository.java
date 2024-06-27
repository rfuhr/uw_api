package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPreco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoPrecoRepository extends UWRepository<TipoPreco, Long> {

}
