package br.com.ultraworks.erp.api.tabela.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.api.tabela.domain.uf.UfDTO;
import br.com.ultraworks.erp.api.tabela.mapper.UfMapper;
import br.com.ultraworks.erp.api.tabela.repository.UfRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UfService extends GenericService<Uf, Long, UfDTO> {

	UfRepository ufRepository;
	UfMapper ufMapper;

	@Autowired
	public UfService(UfRepository ufRepository, UfMapper ufMapper) {
		super(ufRepository, ufMapper);
		this.ufRepository = ufRepository;
		this.ufMapper = ufMapper;
	}

	public Optional<Uf> getBySigla(String sigla) {
		return this.ufRepository.findBySigla(sigla);
	}

}
