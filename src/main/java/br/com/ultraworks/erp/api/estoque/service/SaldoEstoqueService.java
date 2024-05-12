package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.SaldoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.repository.SaldoEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SaldoEstoqueService extends GenericService<SaldoEstoque, Long, SaldoEstoqueDTO> {

	@Autowired
	public SaldoEstoqueService(SaldoEstoqueRepository repository, SaldoEstoqueMapper mapper) {
		super(repository, mapper);
	}

}