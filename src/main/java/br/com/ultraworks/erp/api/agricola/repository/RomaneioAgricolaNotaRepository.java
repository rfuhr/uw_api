package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNota;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface RomaneioAgricolaNotaRepository extends UWRepository<RomaneioAgricolaNota, Long> {

	List<RomaneioAgricolaNota> findByRomaneioAgricolaId(Long romaneioAgricolaId);
}
