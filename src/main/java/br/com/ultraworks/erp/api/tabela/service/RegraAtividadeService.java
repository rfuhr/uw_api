package br.com.ultraworks.erp.api.tabela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroRegraAtividadeRepository;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividadeDTO;
import br.com.ultraworks.erp.api.tabela.mapper.RegraAtividadeMapper;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class RegraAtividadeService extends GenericService<RegraAtividade, Long, RegraAtividadeDTO> {

	private ParceiroRegraAtividadeRepository parceiroRegraAtividadeRepository;
	
	@Autowired
	public RegraAtividadeService(RegraAtividadeRepository repository, RegraAtividadeMapper mapper, ParceiroRegraAtividadeRepository parceiroRegraAtividadeRepository) {
		super(repository, mapper);
		this.parceiroRegraAtividadeRepository = parceiroRegraAtividadeRepository;
	}

	public int getProximoCodigo() {
		return ((RegraAtividadeRepository) repository).getProximoCodigo();
	}

	public Page<RegraAtividade> getAllPaginadaByParceiro(Long parceiroId, LazyParams params) {
		List<Long> ids = this.parceiroRegraAtividadeRepository.buscarIdsRegrasByParceiro(parceiroId);
		if (!ids.isEmpty())
			return getAllPaginadaFilterIds(params, ids);
		else
			return Page.empty();
	}
}
