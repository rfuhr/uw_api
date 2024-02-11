package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoParceiroRepository extends UWRepository<TipoParceiro, Long> {

}
