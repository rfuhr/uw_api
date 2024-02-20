package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProduto;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoProdutoRepository extends UWRepository<TipoProduto, Long> {

}
