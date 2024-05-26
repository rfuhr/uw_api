package br.com.ultraworks.erp.api.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkup;
import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkupDTO;
import br.com.ultraworks.erp.api.comercial.mapper.IndiceMarkupMapper;
import br.com.ultraworks.erp.api.comercial.repository.IndiceMarkupRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class IndiceMarkupService extends GenericService<IndiceMarkup, Long, IndiceMarkupDTO> {

	@Autowired
	public IndiceMarkupService(IndiceMarkupRepository repository, IndiceMarkupMapper mapper) {
		super(repository, mapper);
	}

}