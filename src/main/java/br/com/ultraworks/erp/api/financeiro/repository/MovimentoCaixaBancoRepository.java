package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBanco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MovimentoCaixaBancoRepository extends UWRepository<MovimentoCaixaBanco, Long> {

}
