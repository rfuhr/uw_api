package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiro;
import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiroDTO;
import br.com.ultraworks.erp.api.tabela.mapper.TipoParceiroMapper;
import br.com.ultraworks.erp.api.tabela.repository.TipoParceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoParceiroService extends GenericService<TipoParceiro, Long, TipoParceiroDTO> {

	@Autowired
	public TipoParceiroService(TipoParceiroRepository repository, TipoParceiroMapper mapper) {
		super(repository, mapper);
	}

}