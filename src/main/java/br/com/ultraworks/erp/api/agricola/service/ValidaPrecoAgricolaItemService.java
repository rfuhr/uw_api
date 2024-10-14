package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItem;
import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItemDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaPrecoAgricolaItemMapper;
import br.com.ultraworks.erp.api.agricola.repository.ValidaPrecoAgricolaItemRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ValidaPrecoAgricolaItemService
		extends GenericService<ValidaPrecoAgricolaItem, Long, ValidaPrecoAgricolaItemDTO> {

	@Autowired
	public ValidaPrecoAgricolaItemService(ValidaPrecoAgricolaItemRepository repository,
			ValidaPrecoAgricolaItemMapper mapper) {
		super(repository, mapper);
	}

	public ValidaPrecoAgricolaItem getValidacaoVigente(Long itemId, Long tipoPrecoAgricolaId, LocalDate dataBase) {
		return ((ValidaPrecoAgricolaItemRepository) repository).getValidacaoVigente(itemId, tipoPrecoAgricolaId,
				dataBase);
	}
}