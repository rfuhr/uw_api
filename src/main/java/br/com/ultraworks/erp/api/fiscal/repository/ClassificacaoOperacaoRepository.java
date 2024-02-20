package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ClassificacaoOperacaoRepository extends UWRepository<ClassificacaoOperacao, Long> {

}
