package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoContaCxBcoRepository extends UWRepository<TipoContaCxBco, Long> {

	@Query(value = "SELECT proximo_codigo('tipo_conta_cxbco', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
