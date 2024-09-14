package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.repository.CfopRepository;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedadeDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalPropriedadeMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalPropriedadeRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.OpcaoFiltro;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalPropriedadeService
		extends GenericService<ParceiroLocalPropriedade, Long, ParceiroLocalPropriedadeDTO> {

	ParceiroLocalPropriedadeRepository repository;
	ParceiroLocalPropriedadeMapper mapper;

	@Autowired
	public ParceiroLocalPropriedadeService(ParceiroLocalPropriedadeRepository repository,
			ParceiroLocalPropriedadeMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalPropriedade> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

	public Page<ParceiroLocalPropriedade> getPropriedadesPaginada(Long parceiroLocalId, LazyParams params) {
		params.getFilters().put("parceiroLocalId", OpcaoFiltro.builder().fieldFilter("parceiroLocal.id")
				.matchMode("equals").tipo("integer").value(String.valueOf(parceiroLocalId)).build());
		return getAllPaginada(params);
	}

}
