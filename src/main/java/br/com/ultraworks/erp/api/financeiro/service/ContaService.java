package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.conta.ContaDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.ContaMapper;
import br.com.ultraworks.erp.api.financeiro.repository.ContaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContaService extends GenericService<Conta, Long, ContaDTO> {

	@Autowired
	public ContaService(ContaRepository repository, ContaMapper mapper) {
		super(repository, mapper);
	}
}