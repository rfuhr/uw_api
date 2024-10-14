package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.TipoContratoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoContratoAgricolaService extends GenericService<TipoContratoAgricola, Long, TipoContratoAgricolaDTO> {

	@Autowired
	public TipoContratoAgricolaService(TipoContratoAgricolaRepository repository, TipoContratoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((TipoContratoAgricolaRepository) repository).getProximoCodigo();
	}

}