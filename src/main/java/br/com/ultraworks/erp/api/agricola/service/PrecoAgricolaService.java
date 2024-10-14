package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.PrecoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.PrecoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PrecoAgricolaService extends GenericService<PrecoAgricola, Long, PrecoAgricolaDTO> {

	@Autowired
	public PrecoAgricolaService(PrecoAgricolaRepository repository, PrecoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public PrecoAgricola getPrecoAgricolaVigente(Long itemId, Long tipoPrecoAgricolaId, Long empresaId, Long empresaFilialId, Long departamentoId,
			Long predefinicaoPrecoAgricolaId, Long codNivelClass1, Long codNivelClass2, Long codNivelClass3,
			Long codNivelClass4, LocalDate dataBase) {
		Optional<PrecoAgricola> precoAgricola = ((PrecoAgricolaRepository) repository).getPrecoAgricolaVigente(itemId, tipoPrecoAgricolaId, empresaId, empresaFilialId, departamentoId, predefinicaoPrecoAgricolaId,
				codNivelClass1, codNivelClass2, codNivelClass3, codNivelClass4, dataBase);
		if (precoAgricola.isEmpty()) {
			precoAgricola = ((PrecoAgricolaRepository) repository).getPrecoAgricolaVigente(itemId, tipoPrecoAgricolaId, empresaId, empresaFilialId, null, predefinicaoPrecoAgricolaId,
					codNivelClass1, codNivelClass2, codNivelClass3, codNivelClass4, dataBase);
		}
		if (precoAgricola.isEmpty()) {
			precoAgricola = ((PrecoAgricolaRepository) repository).getPrecoAgricolaVigente(itemId, tipoPrecoAgricolaId, empresaId, null, null, predefinicaoPrecoAgricolaId,
					codNivelClass1, codNivelClass2, codNivelClass3, codNivelClass4, dataBase);
		}
		
		return precoAgricola.orElse(null);
	}
}