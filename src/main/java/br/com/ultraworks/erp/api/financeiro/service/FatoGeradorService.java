package br.com.ultraworks.erp.api.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGeradorDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.FatoGeradorMapper;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class FatoGeradorService extends GenericService<FatoGerador, Long, FatoGeradorDTO> {

	@Autowired
	public FatoGeradorService(FatoGeradorRepository repository, FatoGeradorMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((FatoGeradorRepository) repository).getProximoCodigo();
	}

	public Page<FatoGerador> buscarFatoGeradorValidadoParaContrato(Long itemId, Long grupoOperacaoAgricolaId,
			Long operacaoInternaId, LazyParams params) {
		List<Long> ids = ((FatoGeradorRepository) repository).buscarIdsFatoGeradorValidadoParaContrato(itemId,
				grupoOperacaoAgricolaId, operacaoInternaId);
		if (!ids.isEmpty()) {
			return getAllPaginadaFilterIds(params, ids);
		} else {
			return Page.empty();
		}
	}
}