package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CfopRepository extends UWRepository<Cfop, Long> {

	@Query(value = "SELECT DISTINCT c.id " +
            "FROM operacao_interna oi " +
            "JOIN operacao_interna_fiscal oif ON oif.operacao_interna_id = oi.id " +
            "JOIN operacao_interna_fiscal_cfop oifc ON oifc.operacao_interna_fiscal_id = oif.id " +
            "JOIN cfop c ON c.id = oifc.cfop_id " +
            "WHERE oi.id = ?1", nativeQuery = true)
	List<Long> buscarIdsPermitidosPelaOperacaoInterna(Long operacaoInternaId);
}
