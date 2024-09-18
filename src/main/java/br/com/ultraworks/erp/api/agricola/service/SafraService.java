package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.safra.SafraDTO;
import br.com.ultraworks.erp.api.agricola.mapper.SafraMapper;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SafraService extends GenericService<Safra, Long, SafraDTO> {

	@Autowired
	public SafraService(SafraRepository repository, SafraMapper mapper) {
		super(repository, mapper);
	}

}