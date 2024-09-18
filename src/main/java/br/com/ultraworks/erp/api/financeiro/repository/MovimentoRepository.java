package br.com.ultraworks.erp.api.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MovimentoRepository extends UWRepository<MovimentoFinanceiro, Long> {

	List<MovimentoFinanceiro> findByParcelaIdOrderBySeqMvtoAscSubSeqMvtoAsc(Long parcelaId);
	
	@Query("SELECT mf FROM MovimentoFinanceiro mf " +
            "WHERE mf.seqMvto = mf.parcela.ultimaSeqMvto " +
            "AND mf.parcela.id = :parcelaId " +
            "order by mf.subSeqMvto desc")
    List<MovimentoFinanceiro> buscarListaMovimentoUltimaSequencia(
            @Param("parcelaId") Long parcelaId);
	
	@Query("SELECT mf FROM MovimentoFinanceiro mf " +
            "WHERE mf.parcela.id = :parcelaId " +
            "and   mf.seqMvto = :seqMvto " + 
            "and   mf.subSeqMvto <> 1 " +
            "order by mf.subSeqMvto")
    List<MovimentoFinanceiro> buscarMovimentosAcessorios(
            @Param("parcelaId") Long parcelaId, @Param("seqMvto") int seqMvto);
}
