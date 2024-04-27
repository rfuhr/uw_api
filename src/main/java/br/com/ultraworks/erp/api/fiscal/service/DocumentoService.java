package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.api.fiscal.domain.documento.DocumentoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.DocumentoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.DocumentoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DocumentoService extends GenericService<Documento, Long, DocumentoDTO> {

	@Autowired
	public DocumentoService(DocumentoRepository repository, DocumentoMapper mapper) {
		super(repository, mapper);
	}
	
}