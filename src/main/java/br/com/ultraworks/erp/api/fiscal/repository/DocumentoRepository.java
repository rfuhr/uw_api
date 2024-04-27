package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DocumentoRepository extends UWRepository<Documento, Long> {

}
