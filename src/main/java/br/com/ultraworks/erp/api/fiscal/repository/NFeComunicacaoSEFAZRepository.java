package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeComunicacaoSEFAZ;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface NFeComunicacaoSEFAZRepository extends UWRepository<NFeComunicacaoSEFAZ, Long> {
	
	List<NFeComunicacaoSEFAZ> findByNfeId(Long nfeId, Sort sort);

}
