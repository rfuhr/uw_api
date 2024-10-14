package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividadeDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroRegraAtividadeMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRegraAtividadeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroRegraAtividadeService
		extends GenericService<ParceiroRegraAtividade, Long, ParceiroRegraAtividadeDTO> {

	@Autowired
	public ParceiroRegraAtividadeService(ParceiroRegraAtividadeRepository repository,
			ParceiroRegraAtividadeMapper mapper) {
		super(repository, mapper);
	}

	public List<ParceiroRegraAtividade> getAllByParceiro(Long id) {
		return ((ParceiroRegraAtividadeRepository) repository).findByParceiroId(id);
	}

}
