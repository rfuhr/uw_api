package br.com.ultraworks.erp.api.agricola.repository.query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricolaRomaneioTelaDTO;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecionaItensClassificacaoParaRomaneioQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ItemClassificacaoAgricolaRomaneioTelaDTO> executeSQL(LocalDate dataBase, Long itemId) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("/agricola/selecionaItensClassificacaoParaRomaneio"),
						Tuple.class)
				.setParameter("itemId", itemId).setParameter("dataBase", dataBase).getResultList();

		return resultTuples.stream()
				.map(t -> ItemClassificacaoAgricolaRomaneioTelaDTO.builder().id(t.get("id", Long.class))
						.grupoClassificacaoAgricolaId(t.get("grupoClassificacaoAgricolaId", Long.class))
						.codigo(t.get("codigo", Long.class)).nome(t.get("nome", String.class))
						.tipoCalculoAgricolaIdRomaneio(t.get("tipoCalculoAgricolaIdRomaneio", Long.class))
						.ordem(t.get("ordem", Integer.class))
						.grupoClassificacaoAgricolaNome(t.get("grupoClassificacaoAgricolaNome", String.class))
						.tipoCalculoAgricolaNomeRomaneio(t.get("tipoCalculoAgricolaNomeRomaneio", String.class))
						.build())
				.collect(Collectors.toList());
	}
}
