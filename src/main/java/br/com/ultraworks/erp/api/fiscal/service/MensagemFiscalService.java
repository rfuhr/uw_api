package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.MensagemFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.MensagemFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MensagemFiscalService extends GenericService<MensagemFiscal, Long, MensagemFiscalDTO> {

	@Autowired
	public MensagemFiscalService(MensagemFiscalRepository repository, MensagemFiscalMapper mapper) {
		super(repository, mapper);
	}

}