package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.CacheNFe;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CacheNFeRepository extends UWRepository<CacheNFe, Long> {

	Optional<CacheNFe> findByNfeId(Long nfeId);
}
