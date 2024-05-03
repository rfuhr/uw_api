package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.ItemListaNFeResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaListagemNFeQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ItemListaNFeResponse> executeSQL(Long empresaFilialId) {
		List<Tuple> resultTuples = em.createNativeQuery(SQLUtils.obterQuery("buscaListagemNFe"), Tuple.class)
				.setParameter("empresaFilialId", empresaFilialId).getResultList();

		return resultTuples.stream()
				.map(t -> ItemListaNFeResponse.builder()
						.nfeId(t.get("nfeId", Long.class))
						.dataHoraEmissao(t.get("dataHoraEmissao", Instant.class))
						.chaveNFe(t.get("chaveNFe", String.class)).serie(t.get("serie", Integer.class))
						.numero(t.get("numero", Integer.class)).situacao(t.get("situacao", String.class)).build())
				.collect(Collectors.toList());

	}
}
