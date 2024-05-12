package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.MovimentoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.repository.MovimentoEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MovimentoEstoqueService extends GenericService<MovimentoEstoque, Long, MovimentoEstoqueDTO> {
	
	@Autowired
	public MovimentoEstoqueService(MovimentoEstoqueRepository repository, MovimentoEstoqueMapper mapper) {
		super(repository, mapper);
	}

}