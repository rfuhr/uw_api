package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalTipoIncentFiscalRepository extends UWRepository<ConfigMensagemFiscalTipoIncentFiscal, Long> {

	List<ConfigMensagemFiscalTipoIncentFiscal> findByConfigMensagemFiscalId(Long configMensagemFiscalId);
	
}
