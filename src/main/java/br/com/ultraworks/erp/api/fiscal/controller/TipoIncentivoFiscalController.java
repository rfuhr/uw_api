package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.tipoincentivofiscal.TipoIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.TipoIncentivoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.TipoIncentivoFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/tipo-incentivo-fiscal")
public class TipoIncentivoFiscalController extends GenericController<TipoIncentivoFiscal, Long, TipoIncentivoFiscalDTO> {

	public TipoIncentivoFiscalController(TipoIncentivoFiscalService service, TipoIncentivoFiscalMapper mapper) {
		super(service, mapper);
	}

}