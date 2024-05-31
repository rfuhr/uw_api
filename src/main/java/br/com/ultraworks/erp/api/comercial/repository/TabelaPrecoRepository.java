package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPreco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TabelaPrecoRepository extends UWRepository<TabelaPreco, Long> {

}
