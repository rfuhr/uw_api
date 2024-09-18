package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.pesagemclassificacao.PesagemClassificacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface PesagemClassificacaoRepository extends UWRepository<PesagemClassificacao, Long> {

	List<PesagemClassificacao> findByPesagemId(Long pesagemId);
}
