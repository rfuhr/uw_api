package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalOperInternaRepository extends UWRepository<ConfigMensagemFiscalOperInterna, Long> {

	List<ConfigMensagemFiscalOperInterna> findByConfigMensagemFiscalId(Long configMensagemFiscalId);
	
}
