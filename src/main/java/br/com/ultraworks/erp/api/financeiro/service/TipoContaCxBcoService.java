package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBcoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.TipoContaCxBcoMapper;
import br.com.ultraworks.erp.api.financeiro.repository.TipoContaCxBcoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoContaCxBcoService extends GenericService<TipoContaCxBco, Long, TipoContaCxBcoDTO> {

	@Autowired
	public TipoContaCxBcoService(TipoContaCxBcoRepository repository, TipoContaCxBcoMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((TipoContaCxBcoRepository) repository).getProximoCodigo();
	}
}