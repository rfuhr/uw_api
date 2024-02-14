package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.origem.Origem;
import br.com.ultraworks.erp.api.fiscal.domain.origem.OrigemDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.OrigemMapper;
import br.com.ultraworks.erp.api.fiscal.repository.OrigemRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OrigemService extends GenericService<Origem, Long, OrigemDTO> {

	@Autowired
	public OrigemService(OrigemRepository repository, OrigemMapper mapper) {
		super(repository, mapper);
	}

}