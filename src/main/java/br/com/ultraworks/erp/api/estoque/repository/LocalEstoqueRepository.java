package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface LocalEstoqueRepository extends UWRepository<LocalEstoque, Long> {

}
