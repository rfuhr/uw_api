package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoqueDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaEstoqueMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaEstoqueRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaEstoqueService
		extends GenericService<OperacaoInternaEstoque, Long, OperacaoInternaEstoqueDTO> {

	@Autowired
	public OperacaoInternaEstoqueService(OperacaoInternaEstoqueRepository repository, OperacaoInternaEstoqueMapper mapper) {
		super(repository, mapper);
	}

	public OperacaoInternaEstoque getByOperacaoInterna(Long id) {
		return ((OperacaoInternaEstoqueRepository) repository).findByOperacaoInternaId(id);
	}
}