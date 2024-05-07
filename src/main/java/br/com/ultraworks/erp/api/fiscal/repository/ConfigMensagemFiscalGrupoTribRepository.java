package br.com.ultraworks.erp.api.fiscal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalGrupoTribRepository extends UWRepository<ConfigMensagemFiscalGrupoTrib, Long> {

	List<ConfigMensagemFiscalGrupoTrib> findByConfigMensagemFiscalId(Long configMensagemFiscalId);
	
}
