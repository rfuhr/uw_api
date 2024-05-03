package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.mensagemfiscal.MensagemFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MensagemFiscalRepository extends UWRepository<MensagemFiscal, Long> {

}
