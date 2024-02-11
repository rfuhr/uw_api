package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface FatoGeradorRepository extends UWRepository<FatoGerador, Long> {

}
