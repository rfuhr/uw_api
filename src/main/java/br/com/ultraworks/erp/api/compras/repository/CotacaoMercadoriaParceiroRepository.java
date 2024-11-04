package br.com.ultraworks.erp.api.compras.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CotacaoMercadoriaParceiroRepository extends UWRepository<CotacaoMercadoriaParceiro, Long> {

	List<CotacaoMercadoriaParceiro> findByCotacaoMercadoriaId(Long cotacaoMercadoriaId);
}
