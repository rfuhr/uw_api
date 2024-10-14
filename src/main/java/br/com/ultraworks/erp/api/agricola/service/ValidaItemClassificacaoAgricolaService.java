package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaItemClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ValidaItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ValidaItemClassificacaoAgricolaService
		extends GenericService<ValidaItemClassificacaoAgricola, Long, ValidaItemClassificacaoAgricolaDTO> {

	@Autowired
	public ValidaItemClassificacaoAgricolaService(ValidaItemClassificacaoAgricolaRepository repository,
			ValidaItemClassificacaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public List<ValidaItemClassificacaoAgricola> getValidacaoVigenteUsoRomaneio(Long itemId,
			Long itemClassificacaoAgricolaId, LocalDate dataBase) {
		return ((ValidaItemClassificacaoAgricolaRepository) repository).getValidacaoVigenteUsoRomaneio(itemId,
				itemClassificacaoAgricolaId, dataBase);
	}

	public List<ValidaItemClassificacaoAgricola> getItensValidacaoVigenteUsoRomaneio(Long itemId, LocalDate dataBase) {
		return ((ValidaItemClassificacaoAgricolaRepository) repository).getItensValidacaoVigenteUsoRomaneio(itemId,
				dataBase);
	}
}