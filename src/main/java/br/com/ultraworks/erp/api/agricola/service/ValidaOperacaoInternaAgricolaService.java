package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaOperacaoInternaAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ValidaOperacaoInternaAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ValidaOperacaoInternaAgricolaService
		extends GenericService<ValidaOperacaoInternaAgricola, Long, ValidaOperacaoInternaAgricolaDTO> {

	@Autowired
	public ValidaOperacaoInternaAgricolaService(ValidaOperacaoInternaAgricolaRepository repository,
			ValidaOperacaoInternaAgricolaMapper mapper) {
		super(repository, mapper);
	}
	
	public ValidaOperacaoInternaAgricola getValidacaoVigente(Long itemId, Long operacaoInternaId, Long grupoOperacaoAgricolaId, LocalDate dataBase) {
		return ((ValidaOperacaoInternaAgricolaRepository) repository).getValidacaoVigente(itemId, operacaoInternaId, grupoOperacaoAgricolaId, dataBase);
	}

}