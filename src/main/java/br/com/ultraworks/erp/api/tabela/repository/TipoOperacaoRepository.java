package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoOperacaoRepository extends UWRepository<TipoOperacao, Long> {

}
