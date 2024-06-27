package br.com.ultraworks.erp.api.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoempresafilial.TabelaPrecoEmpresaFilial;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TabelaPrecoEmpresaFilialRepository extends UWRepository<TabelaPrecoEmpresaFilial, Long> {
	
	List<TabelaPrecoEmpresaFilial> findByTabelaPrecoId(Long tabelaPrecoId);

}
