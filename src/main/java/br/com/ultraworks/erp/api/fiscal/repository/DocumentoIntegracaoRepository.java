package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.documentointegracao.DocumentoIntegracao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DocumentoIntegracaoRepository extends UWRepository<DocumentoIntegracao, Long> {
	
	List<DocumentoIntegracao> findByDocumentoId(Long documentoId);

}
