package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilial;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilialDTO;
import br.com.ultraworks.erp.api.comercial.mapper.TabelaPrecoEmpresaFilialMapper;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoEmpresaFilialRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TabelaPrecoEmpresaFilialService extends GenericService<TabelaPrecoEmpresaFilial, Long, TabelaPrecoEmpresaFilialDTO> {

	TabelaPrecoEmpresaFilialRepository repository;
	
	@Autowired
	public TabelaPrecoEmpresaFilialService(TabelaPrecoEmpresaFilialRepository repository, 
			TabelaPrecoEmpresaFilialMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<TabelaPrecoEmpresaFilial> getAllByTabelaPreco(Long id) {
		List<TabelaPrecoEmpresaFilial> listRegistros = new ArrayList<>();

		repository.findByTabelaPrecoId(id).forEach(config -> {
			listRegistros.add(config);
		});
		return listRegistros;
	}
	
	@Override
	public TabelaPrecoEmpresaFilial save(TabelaPrecoEmpresaFilial entity) {
		return super.save(entity);
	}

}