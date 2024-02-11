package br.com.ultraworks.erp.api.organograma.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DepartamentoRepository extends UWRepository<Departamento, Long> {

}
