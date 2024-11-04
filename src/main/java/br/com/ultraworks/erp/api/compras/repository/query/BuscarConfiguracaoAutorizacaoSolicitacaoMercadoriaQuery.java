package br.com.ultraworks.erp.api.compras.repository.query;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscarConfiguracaoAutorizacaoSolicitacaoMercadoriaQuery {

	EntityManager em;

	public ConfigAutorizacaoSolicitacaoMercadoria executeSQL(Long empresaId, Long empresaFilialId, Long departamentoId,
			Long grupoContabilId) {
		return (ConfigAutorizacaoSolicitacaoMercadoria) em
				.createNativeQuery(SQLUtils.obterQuery("compra/buscarConfiguracaoAutorizacaoSolicitacaoMercadoria"),
						ConfigAutorizacaoSolicitacaoMercadoria.class)
				.setParameter("empresaId", empresaId).setParameter("empresaFilialId", empresaFilialId)
				.setParameter("departamentoId", departamentoId).setParameter("grupoContabilId", grupoContabilId)
				.getSingleResult();

	}
}
