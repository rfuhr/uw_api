package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoDocumentoRepository extends UWRepository<TipoDocumento, Long> {

	TipoDocumento findByCodigoReceita(String codigoReceita);
}
