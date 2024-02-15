package br.com.ultraworks.erp.api.estoque.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.estoque.domain.marca.Marca;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MarcaRepository extends UWRepository<Marca, Long> {

}
