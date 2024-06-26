package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoTituloRepository extends UWRepository<TipoTitulo, Long> {

	@Query(value = "SELECT proximo_codigo('tipo_titulo', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
