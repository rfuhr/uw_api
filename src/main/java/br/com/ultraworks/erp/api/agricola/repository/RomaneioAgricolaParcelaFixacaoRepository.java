package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface RomaneioAgricolaParcelaFixacaoRepository extends UWRepository<RomaneioAgricolaParcelaFixacao, Long> {

	List<RomaneioAgricolaParcelaFixacao> findByRomaneioAgricolaId(Long romaneioAgricolaId);
}
