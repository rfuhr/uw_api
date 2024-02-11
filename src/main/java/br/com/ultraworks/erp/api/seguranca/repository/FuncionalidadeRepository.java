package br.com.ultraworks.erp.api.seguranca.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.Funcionalidade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface FuncionalidadeRepository extends UWRepository<Funcionalidade, Long> {

}
