package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkup;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface IndiceMarkupRepository extends UWRepository<IndiceMarkup, Long> {

}
