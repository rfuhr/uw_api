package br.com.ultraworks.erp.api.compras.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CotacaoMercadoriaRepository extends UWRepository<CotacaoMercadoria, Long> {

}
