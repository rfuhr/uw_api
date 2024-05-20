package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTituloDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.TipoTituloMapper;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoTituloService extends GenericService<TipoTitulo, Long, TipoTituloDTO> {

	@Autowired
	public TipoTituloService(TipoTituloRepository repository, TipoTituloMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((TipoTituloRepository) repository).getProximoCodigo();
	}

}