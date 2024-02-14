package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.Enquadramento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface EnquadramentoRepository extends UWRepository<Enquadramento, Long> {

}
