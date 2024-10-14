package br.com.ultraworks.erp.api.comercial.repository.query;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaPrecoItemAtualQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public BigDecimal executeSQL(Long itemId) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("buscarPrecoItemAtual"), Tuple.class)
				.setParameter("itemId", itemId)
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			return new BigDecimal(resultTuples.iterator().next().get("valor").toString());
		}
		return BigDecimal.ZERO;
	}
}
