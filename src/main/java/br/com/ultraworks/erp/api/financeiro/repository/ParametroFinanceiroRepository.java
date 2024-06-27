package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParametroFinanceiroRepository extends UWRepository<ParametroFinanceiro, Long> {

	@Query("SELECT pf FROM ParametroFinanceiro pf " +
            "WHERE pf.tipoTitulo.id = :tipoTituloId " +
            "AND pf.caracteristicaMovimentoFinanceiro.id = :caracteristicaMovimentoFinanceiroId " +
            "AND pf.carteiraFinanceira.id = :carteiraFinanceiraId " +
            "AND pf.fatoGerador.id = :fatoGeradorId " +
            "AND pf.operacaoMovimentoFinanceiro.id = :operacaoMovimentoFinanceiroId " +
            "AND pf.operacaoAcessoriaFinanceira.id = :operacaoAcessoriaFinanceiraId ")
    ParametroFinanceiro findByParametros(
            @Param("tipoTituloId") Long tipoTituloId,
            @Param("caracteristicaMovimentoFinanceiroId") Long caracteristicaMovimentoFinanceiroId,
            @Param("carteiraFinanceiraId") Long carteiraFinanceiraId,
            @Param("fatoGeradorId") Long fatoGeradorId,
            @Param("operacaoMovimentoFinanceiroId") Long operacaoMovimentoFinanceiroId,
            @Param("operacaoAcessoriaFinanceiraId") Long operacaoAcessoriaFinanceiraId);
}
