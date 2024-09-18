package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.banco.Banco;
import br.com.ultraworks.erp.api.financeiro.domain.banco.BancoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.BancoMapper;
import br.com.ultraworks.erp.api.financeiro.repository.BancoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class BancoService extends GenericService<Banco, Long, BancoDTO> {

	@Autowired
	public BancoService(BancoRepository repository, BancoMapper mapper) {
		super(repository, mapper);
	}
}