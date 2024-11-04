package br.com.ultraworks.erp.api.tabela.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.autorizacao.Autorizacao;
import br.com.ultraworks.erp.api.tabela.domain.tipoautorizacao.TipoAutorizacao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface AutorizacaoRepository extends UWRepository<Autorizacao, Long> {

	List<Autorizacao> getAutorizacaoByDocumentoOrigemIdAndTipoAutorizacao(Long documentoOrigemId, TipoAutorizacao tipoAutorizacao);
}
