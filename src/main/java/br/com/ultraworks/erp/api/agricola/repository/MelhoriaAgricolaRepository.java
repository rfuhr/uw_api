package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MelhoriaAgricolaRepository extends UWRepository<MelhoriaAgricola, Long> {

	@Query(value = "select m.*			 																"
			+ " from   melhoria_agricola m																"
			+ " where  m.item_id = :itemId 																"
			+ " and    :dataBase between m.data_inicio_vigencia and m.data_final_vigencia				", nativeQuery = true)
	List<MelhoriaAgricola> getItensVigentes(Long itemId, LocalDate dataBase);
}
