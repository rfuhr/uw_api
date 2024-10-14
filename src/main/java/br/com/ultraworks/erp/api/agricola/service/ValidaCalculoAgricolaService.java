package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ValidaCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ValidaCalculoAgricolaService
		extends GenericService<ValidaCalculoAgricola, Long, ValidaCalculoAgricolaDTO> {

	@Autowired
	public ValidaCalculoAgricolaService(ValidaCalculoAgricolaRepository repository,
			ValidaCalculoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public ValidaCalculoAgricola getValidacaoVigente(Long itemId, Long tipoCalculoAgricolaId, Long operacaoInternaId,
			Long grupoOperacaoAgricolaId, Long regraAtividadeId, LocalDate dataBase) {
		return ((ValidaCalculoAgricolaRepository) repository).getValidacaoVigente(itemId, tipoCalculoAgricolaId,
				operacaoInternaId, grupoOperacaoAgricolaId, regraAtividadeId, dataBase);
	}
	
	public List<ValidaCalculoAgricola> getValidacoesVigente(Long itemId, Long operacaoInternaId,
			Long grupoOperacaoAgricolaId, Long regraAtividadeId, LocalDate dataBase) {
		return ((ValidaCalculoAgricolaRepository) repository).getValidacoesTipoCalculoVigente(itemId, 
				operacaoInternaId, grupoOperacaoAgricolaId, regraAtividadeId, dataBase);
	}
}