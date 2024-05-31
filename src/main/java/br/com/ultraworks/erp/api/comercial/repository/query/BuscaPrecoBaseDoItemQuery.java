package br.com.ultraworks.erp.api.comercial.repository.query;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.calculoprecos.Precos;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaPrecoBaseDoItemQuery {

	EntityManager em;

	@SuppressWarnings({ "unchecked", "removal" })
	public Precos executeSQL(Precos precos) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("buscaPrecoBaseDoItem"), Tuple.class)
				.setParameter("tipoPrecoId", precos.getTipoPrecoId())
				.setParameter("itemId", precos.getItemId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			Long configCalculoPrecoId = new Long(resultTuples.iterator().next().get("configcalculoprecoid").toString());
			precos.setConfigCalculoPrecoId(configCalculoPrecoId);
			if (resultTuples.iterator().next().get("precobase") != null) {
				BigDecimal precoBase = new BigDecimal(resultTuples.iterator().next().get("precobase").toString());
				precos.setValorPrecoBase(precoBase);
			}
		}
		return precos;
	}
}
