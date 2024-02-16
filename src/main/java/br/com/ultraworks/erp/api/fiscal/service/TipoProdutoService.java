package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProduto;
import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProdutoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.TipoProdutoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.TipoProdutoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoProdutoService extends GenericService<TipoProduto, Long, TipoProdutoDTO> {

	@Autowired
	public TipoProdutoService(TipoProdutoRepository repository, TipoProdutoMapper mapper) {
		super(repository, mapper);
	}

}