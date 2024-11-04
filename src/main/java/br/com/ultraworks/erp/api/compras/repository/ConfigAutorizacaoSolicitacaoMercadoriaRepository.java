package br.com.ultraworks.erp.api.compras.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigAutorizacaoSolicitacaoMercadoriaRepository extends UWRepository<ConfigAutorizacaoSolicitacaoMercadoria, Long> {

}
