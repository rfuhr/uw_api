package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.ncm.Ncm;
import br.com.ultraworks.erp.api.fiscal.domain.ncm.NcmDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.NcmMapper;
import br.com.ultraworks.erp.api.fiscal.repository.NcmRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class NcmService extends GenericService<Ncm, Long, NcmDTO> {

	@Autowired
	public NcmService(NcmRepository repository, NcmMapper mapper) {
		super(repository, mapper);
	}

}