package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMensagemFiscalRepository extends UWRepository<ConfigMensagemFiscal, Long> {

}
