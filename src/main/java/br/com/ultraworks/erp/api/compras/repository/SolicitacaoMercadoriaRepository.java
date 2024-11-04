package br.com.ultraworks.erp.api.compras.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoria;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SolicitacaoMercadoriaRepository extends UWRepository<SolicitacaoMercadoria, Long> {


}
