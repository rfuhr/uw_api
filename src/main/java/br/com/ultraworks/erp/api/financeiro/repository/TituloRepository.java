package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TituloRepository extends UWRepository<Titulo, Long> {

}
