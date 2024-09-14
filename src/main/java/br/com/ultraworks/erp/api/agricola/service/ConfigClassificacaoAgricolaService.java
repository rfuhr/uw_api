package br.com.ultraworks.erp.api.agricola.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ConfigClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ConfigClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigClassificacaoAgricolaService
		extends GenericService<ConfigClassificacaoAgricola, Long, ConfigClassificacaoAgricolaDTO> {

	@Autowired
	public ConfigClassificacaoAgricolaService(ConfigClassificacaoAgricolaRepository repository,
			ConfigClassificacaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public ConfigClassificacaoAgricola findByClassificacaoAgricola(Long tipoClassificacaoAgricolaId, Long produtoId,
			Long safraId, BigDecimal valor) {
		return ((ConfigClassificacaoAgricolaRepository) repository)
				.findByClassificacaoAgricola(tipoClassificacaoAgricolaId, produtoId, safraId, valor);
	}

}