package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.LocalEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.repository.LocalEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class LocalEstoqueService extends GenericService<LocalEstoque, Long, LocalEstoqueDTO> {

	@Autowired
	public LocalEstoqueService(LocalEstoqueRepository repository, LocalEstoqueMapper mapper) {
		super(repository, mapper);
	}

}