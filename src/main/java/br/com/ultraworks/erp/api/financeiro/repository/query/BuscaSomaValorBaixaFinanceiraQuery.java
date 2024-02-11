package br.com.ultraworks.erp.api.financeiro.repository.query;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaSomaValorBaixaFinanceiraQuery {
	
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public BigDecimal executeSQL(long parcelaId, int sequenciaMovimento, Long empresaId) {
		Tuple resultTuples = (Tuple) em.createNativeQuery(SQLUtils.obterQuery("buscaSomaValorBaixaFinanceira"), Tuple.class)
				.setParameter("parcelaId", parcelaId)
				.setParameter("sequenciaMovimento", sequenciaMovimento)
				.setParameter("empresaId", empresaId)
				.getSingleResult();

		return resultTuples.get("valor", BigDecimal.class);
	}	
}
