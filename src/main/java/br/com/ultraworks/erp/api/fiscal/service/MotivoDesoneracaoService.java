package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracao;
import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracaoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.MotivoDesoneracaoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.MotivoDesoneracaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MotivoDesoneracaoService extends GenericService<MotivoDesoneracao, Long, MotivoDesoneracaoDTO> {

	@Autowired
	public MotivoDesoneracaoService(MotivoDesoneracaoRepository repository, MotivoDesoneracaoMapper mapper) {
		super(repository, mapper);
	}

}