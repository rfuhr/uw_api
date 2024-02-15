package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.CfopDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.CfopMapper;
import br.com.ultraworks.erp.api.fiscal.repository.CfopRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CfopService extends GenericService<Cfop, Long, CfopDTO> {

	@Autowired
	public CfopService(CfopRepository repository, CfopMapper mapper) {
		super(repository, mapper);
	}

}