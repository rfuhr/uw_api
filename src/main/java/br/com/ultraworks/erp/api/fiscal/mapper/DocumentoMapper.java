package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.api.fiscal.domain.documento.DocumentoDTO;
import br.com.ultraworks.erp.api.fiscal.repository.DocumentoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class DocumentoMapper extends GenericMapper<Documento, DocumentoDTO> {

	public DocumentoMapper(DocumentoRepository documentoRepository) {
		super(documentoRepository, Documento::new, DocumentoDTO::new);
    }

	@Override
	protected void setValuesToEntity(DocumentoDTO dto, Documento entity) {
		entity.setId(dto.getId());
	}

	@Override
	protected void setValuesToDto(Documento entity, DocumentoDTO dto) {
		dto.setId(entity.getId());
	}	
}