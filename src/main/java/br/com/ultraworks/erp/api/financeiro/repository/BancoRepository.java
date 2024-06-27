package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.banco.Banco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface BancoRepository extends UWRepository<Banco, Long> {

}
