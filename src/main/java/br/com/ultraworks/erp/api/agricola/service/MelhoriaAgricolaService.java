package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.MelhoriaAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.MelhoriaAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MelhoriaAgricolaService extends GenericService<MelhoriaAgricola, Long, MelhoriaAgricolaDTO> {

	@Autowired
	public MelhoriaAgricolaService(MelhoriaAgricolaRepository repository, MelhoriaAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public List<MelhoriaAgricola> getItensVigentes(Long itemId, LocalDate dataBase) {
		return ((MelhoriaAgricolaRepository) repository).getItensVigentes(itemId, dataBase);
	}
}