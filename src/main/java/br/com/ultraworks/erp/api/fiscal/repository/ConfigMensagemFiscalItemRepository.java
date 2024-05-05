package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalItemRepository extends UWRepository<ConfigMensagemFiscalItem, Long> {

	List<ConfigMensagemFiscalItem> findByConfigMensagemFiscalId(Long configMensagemFiscalId);
	
}
