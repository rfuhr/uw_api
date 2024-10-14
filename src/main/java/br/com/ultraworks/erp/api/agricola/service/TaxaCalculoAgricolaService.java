package br.com.ultraworks.erp.api.agricola.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TaxaCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.TaxaCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TaxaCalculoAgricolaService extends GenericService<TaxaCalculoAgricola, Long, TaxaCalculoAgricolaDTO> {

	@Autowired
	public TaxaCalculoAgricolaService(TaxaCalculoAgricolaRepository repository, TaxaCalculoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public TaxaCalculoAgricola getValidacaoVigente(Long itemId, Long tipoCalculoAgricolaId, Long regraAtividadeId,
			BigDecimal indiceReferencia, LocalDate dataBase) {
		return ((TaxaCalculoAgricolaRepository) repository).getValidacaoVigente(itemId, tipoCalculoAgricolaId,
				regraAtividadeId, indiceReferencia, dataBase);
	}
}