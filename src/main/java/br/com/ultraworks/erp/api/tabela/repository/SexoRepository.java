package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.sexo.Sexo;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SexoRepository extends UWRepository<Sexo, Long> {

}
