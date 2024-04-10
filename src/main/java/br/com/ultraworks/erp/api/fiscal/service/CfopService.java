package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.CfopDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.CfopMapper;
import br.com.ultraworks.erp.api.fiscal.repository.CfopRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CfopService extends GenericService<Cfop, Long, CfopDTO> {

	@Autowired
	public CfopService(CfopRepository repository, CfopMapper mapper) {
		super(repository, mapper);
	}
	
	public Page<Cfop> getAllPaginadabyOperacaoInternaId(Long operacaoInternaId, LazyParams params) {
		List<Long> ids = ((CfopRepository) repository).buscarIdsPermitidosPelaOperacaoInterna(operacaoInternaId);
		return getAllPaginadaFilterIds(params, ids);
	}

}