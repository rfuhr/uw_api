package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.balanca.Balanca;
import br.com.ultraworks.erp.api.agricola.domain.pesagem.Pesagem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface PesagemRepository extends UWRepository<Pesagem, Long> {
	
}
