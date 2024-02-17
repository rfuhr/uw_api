package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributariaDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.SituacaoTributariaMapper;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SituacaoTributariaService extends GenericService<SituacaoTributaria, Long, SituacaoTributariaDTO> {

	@Autowired
	public SituacaoTributariaService(SituacaoTributariaRepository repository, SituacaoTributariaMapper mapper) {
		super(repository, mapper);
	}

}