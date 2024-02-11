package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInterna;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoOperacaoInternaRepository extends UWRepository<GrupoOperacaoInterna, Long> {

}
