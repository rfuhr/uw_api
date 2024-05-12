package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.TipoLocalEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.repository.TipoLocalEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoLocalEstoqueService extends GenericService<TipoLocalEstoque, Long, TipoLocalEstoqueDTO> {

	@Autowired
	public TipoLocalEstoqueService(TipoLocalEstoqueRepository repository, TipoLocalEstoqueMapper mapper) {
		super(repository, mapper);
	}

}