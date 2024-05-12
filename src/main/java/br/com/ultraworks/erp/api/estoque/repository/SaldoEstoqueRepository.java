package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoque;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SaldoEstoqueRepository extends UWRepository<SaldoEstoque, Long> {

}
