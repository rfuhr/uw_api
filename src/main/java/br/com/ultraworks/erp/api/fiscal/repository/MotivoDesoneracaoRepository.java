package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MotivoDesoneracaoRepository extends UWRepository<MotivoDesoneracao, Long> {

}
