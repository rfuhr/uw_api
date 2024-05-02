package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalConfigFiscalRepository extends UWRepository<ConfigMensagemFiscalConfigFiscal, Long> {

	List<ConfigMensagemFiscalConfigFiscal> findByConfigMensagemFiscalId(Long configMensagemFiscalId);
	
}
