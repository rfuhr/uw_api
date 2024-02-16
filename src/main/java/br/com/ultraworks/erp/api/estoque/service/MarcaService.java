package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.marca.Marca;
import br.com.ultraworks.erp.api.estoque.domain.marca.MarcaDTO;
import br.com.ultraworks.erp.api.estoque.mapper.MarcaMapper;
import br.com.ultraworks.erp.api.estoque.repository.MarcaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MarcaService extends GenericService<Marca, Long, MarcaDTO> {

	@Autowired
	public MarcaService(MarcaRepository repository, MarcaMapper mapper) {
		super(repository, mapper);
	}

}