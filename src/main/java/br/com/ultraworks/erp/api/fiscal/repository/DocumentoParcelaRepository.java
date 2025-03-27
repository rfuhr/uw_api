package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.documentoparcela.DocumentoParcela;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DocumentoParcelaRepository extends UWRepository<DocumentoParcela, Long> {
	
	List<DocumentoParcela> findByDocumentoId(Long documentoId);

}
