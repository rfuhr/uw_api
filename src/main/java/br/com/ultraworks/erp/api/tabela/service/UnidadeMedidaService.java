package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedida;
import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedidaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.UnidadeMedidaMapper;
import br.com.ultraworks.erp.api.tabela.repository.UnidadeMedidaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UnidadeMedidaService extends GenericService<UnidadeMedida, Long, UnidadeMedidaDTO> {

	@Autowired
	public UnidadeMedidaService(UnidadeMedidaRepository repository, UnidadeMedidaMapper mapper) {
		super(repository, mapper);
	}

}
