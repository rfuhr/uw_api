package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.linha.Linha;
import br.com.ultraworks.erp.api.estoque.domain.linha.LinhaDTO;
import br.com.ultraworks.erp.api.estoque.mapper.LinhaMapper;
import br.com.ultraworks.erp.api.estoque.repository.LinhaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class LinhaService extends GenericService<Linha, Long, LinhaDTO> {

	@Autowired
	public LinhaService(LinhaRepository repository, LinhaMapper mapper) {
		super(repository, mapper);
	}

}