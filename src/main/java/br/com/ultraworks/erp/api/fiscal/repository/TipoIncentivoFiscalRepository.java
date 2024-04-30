package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoIncentivoFiscalRepository extends UWRepository<TipoIncentivoFiscal, Long> {

}
