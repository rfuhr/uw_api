package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.documentoItem.DocumentoItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DocumentoItemRepository extends UWRepository<DocumentoItem, Long> {
	
	List<DocumentoItem> findByDocumentoId(Long documentoId);

}
