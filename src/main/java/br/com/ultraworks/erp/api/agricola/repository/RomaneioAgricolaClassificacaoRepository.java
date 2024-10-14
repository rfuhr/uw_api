package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacao;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface RomaneioAgricolaClassificacaoRepository extends UWRepository<RomaneioAgricolaClassificacao, Long> {

	List<RomaneioAgricolaClassificacao> findByRomaneioAgricolaId(Long romaneioAgricolaId);
}
