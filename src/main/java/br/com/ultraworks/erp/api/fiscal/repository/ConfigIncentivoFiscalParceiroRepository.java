package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigIncentivoFiscalParceiroRepository extends UWRepository<ConfigIncentivoFiscalParceiro, Long> {

	List<ConfigIncentivoFiscalParceiro> findByConfigIncentivoFiscalId(Long configIncentivoFiscalId);
	
}
