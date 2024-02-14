package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.Enquadramento;
import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.EnquadramentoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.EnquadramentoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.EnquadramentoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EnquadramentoService extends GenericService<Enquadramento, Long, EnquadramentoDTO> {

	@Autowired
	public EnquadramentoService(EnquadramentoRepository repository, EnquadramentoMapper mapper) {
		super(repository, mapper);
	}

}