package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MovimentoEstoqueRepository extends UWRepository<MovimentoEstoque, Long> {

}
