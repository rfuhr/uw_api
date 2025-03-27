package br.com.ultraworks.erp.api.fiscal.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DocumentoRepository extends UWRepository<Documento, Long> {
	
	@Query(value = "select dc.id "
			+ "from documento dc "
			+ "where dc.numero = :numero "
			+ "and dc.data_documento = :dataDocumento "
			+ "and dc.parceiro_local_id = :parceiroLocalId "
			+ "and dc.operacao_interna_id = :operacaoInternaId "
			+ "and dc.valor = :valor "
			+ "and (1 = :validaId or dc.id <> :id)   limit 1   						", nativeQuery = true)
	Long verificaDuplicidade(Long numero, LocalDate dataDocumento, Long parceiroLocalId, Long operacaoInternaId, BigDecimal valor,
			int validaId, Long id);

	Documento findByNfeId(Long nfeId);
	
}
