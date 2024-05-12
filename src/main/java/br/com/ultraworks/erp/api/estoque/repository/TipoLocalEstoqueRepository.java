package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoque;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoLocalEstoqueRepository extends UWRepository<TipoLocalEstoque, Long> {

}
