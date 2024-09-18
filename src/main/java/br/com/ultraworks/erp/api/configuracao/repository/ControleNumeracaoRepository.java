package br.com.ultraworks.erp.api.configuracao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.controlenumeracao.ControleNumeracao;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ControleNumeracaoRepository extends UWRepository<ControleNumeracao, Long> {

	@Query(value = "SELECT obter_proximo_numero(:empresaId, :empresaFilialId, :tipoDocumentoId, :serie)", nativeQuery = true)
	int getProximoNumero(Long empresaId, Long empresaFilialId, Long tipoDocumentoId, int serie);
}