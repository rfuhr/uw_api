package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalSituacTribRepository extends UWRepository<ConfigMensagemFiscalSituacTrib, Long> {

	List<ConfigMensagemFiscalSituacTrib> findByConfigMensagemFiscalId(Long configMensagemFiscalId);
	
}
