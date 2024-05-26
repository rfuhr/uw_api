package br.com.ultraworks.erp.api.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPreco;
import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.mapper.TipoPrecoMapper;
import br.com.ultraworks.erp.api.comercial.repository.TipoPrecoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoPrecoService extends GenericService<TipoPreco, Long, TipoPrecoDTO> {

	@Autowired
	public TipoPrecoService(TipoPrecoRepository repository, TipoPrecoMapper mapper) {
		super(repository, mapper);
	}

}