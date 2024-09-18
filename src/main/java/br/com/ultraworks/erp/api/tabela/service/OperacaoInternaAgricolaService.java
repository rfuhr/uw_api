package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricola;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaAgricolaMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaAgricolaService
		extends GenericService<OperacaoInternaAgricola, Long, OperacaoInternaAgricolaDTO> {

	@Autowired
	public OperacaoInternaAgricolaService(OperacaoInternaAgricolaRepository repository,
			OperacaoInternaAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public OperacaoInternaAgricola getByOperacaoInterna(Long id) {
		return ((OperacaoInternaAgricolaRepository) repository).findByOperacaoInternaId(id);
	}
}