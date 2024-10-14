package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface RomaneioAgricolaRepository extends UWRepository<RomaneioAgricola, Long> {

	@Query(value = "select	distinct ra.id		 																"
			+ " from	romaneio_agricola ra															"
			+ "		join departamento d on d.id = ra.departamento_id									"
			+ "		join empresa_filial ef on ef.id = d.empresa_filial_id								"
			+ "		join item i on i.id = ra.item_id													"
			+ " where	ef.id = :empresaFilialId 														"
			+ " and     (d.id = :departamentoId or :departamentoId is null)								"
			+ " and     ra.item_id = :itemId															"
			+ " and     ra.saldo_fixar > 0																"
			+ " and     ra.situacao = '3'																"
			+ " order by ra.numero																			", nativeQuery = true)
	List<Long> buscarIdsRomaneiosParaFixacao(Long empresaFilialId, Long departamentoId, Long itemId);

}
