package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributario;
import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributarioDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.RegimeTributarioMapper;
import br.com.ultraworks.erp.api.fiscal.repository.RegimeTributarioRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RegimeTributarioService extends GenericService<RegimeTributario, Long, RegimeTributarioDTO> {

	@Autowired
	public RegimeTributarioService(RegimeTributarioRepository repository, RegimeTributarioMapper mapper) {
		super(repository, mapper);
	}

}