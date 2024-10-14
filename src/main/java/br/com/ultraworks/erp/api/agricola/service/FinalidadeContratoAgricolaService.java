package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.FinalidadeContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.FinalidadeContratoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class FinalidadeContratoAgricolaService
		extends GenericService<FinalidadeContratoAgricola, Long, FinalidadeContratoAgricolaDTO> {

	@Autowired
	public FinalidadeContratoAgricolaService(FinalidadeContratoAgricolaRepository repository,
			FinalidadeContratoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((FinalidadeContratoAgricolaRepository) repository).getProximoCodigo();
	}

}