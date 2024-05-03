package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.TipoIncentivoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.TipoIncentivoFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoIncentivoFiscalService extends GenericService<TipoIncentivoFiscal, Long, TipoIncentivoFiscalDTO> {

	@Autowired
	public TipoIncentivoFiscalService(TipoIncentivoFiscalRepository repository, TipoIncentivoFiscalMapper mapper) {
		super(repository, mapper);
	}

}