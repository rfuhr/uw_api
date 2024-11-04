package br.com.ultraworks.erp.api.compras.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificado;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ItemSimplificadoRepository extends UWRepository<ItemSimplificado, Long> {

	@Query(value = "SELECT proximo_codigo('item_simplificado', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
