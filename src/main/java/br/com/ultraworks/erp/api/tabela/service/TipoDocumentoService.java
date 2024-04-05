package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumentoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.TipoDocumentoMapper;
import br.com.ultraworks.erp.api.tabela.repository.TipoDocumentoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoDocumentoService extends GenericService<TipoDocumento, Long, TipoDocumentoDTO> {

	@Autowired
	public TipoDocumentoService(TipoDocumentoRepository repository, TipoDocumentoMapper mapper) {
		super(repository, mapper);
	}

	public TipoDocumento findByCodigoReceita(String codigoReceita) {
		return ((TipoDocumentoRepository) repository).findByCodigoReceita(codigoReceita);
	}
}