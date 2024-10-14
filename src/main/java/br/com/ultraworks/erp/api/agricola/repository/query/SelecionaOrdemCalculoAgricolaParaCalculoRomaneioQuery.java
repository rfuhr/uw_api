package br.com.ultraworks.erp.api.agricola.repository.query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.query.SelecionaOrdemCalculoAgricolaParaCalculo;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SelecionaOrdemCalculoAgricolaParaCalculo> executeSQL(Long itemId, Long itemClassficacaoAgricolaId, Long operacaoInternaId,
			LocalDate dataBase) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("/agricola/selecionaOrdemCalculoAgricolaParaCalculoRomaneio"),
						Tuple.class)
				.setParameter("itemId", itemId)
				.setParameter("itemClassificacaoAgricolaId", itemClassficacaoAgricolaId)
				.setParameter("operacaoInternaId", operacaoInternaId)
				.setParameter("dataBase", dataBase).getResultList();

		return resultTuples.stream()
				.map(t -> SelecionaOrdemCalculoAgricolaParaCalculo.builder()
						.indetificacaoDocumentoAgricola(t.get("identificacao_documento_agricola", String.class))
						.ordem(t.get("ordem", Integer.class))
						.tipoCalculoAgricolaId(t.get("tipo_calculo_agricola_id", Long.class))
						.dataInicioVigencia(t.get("data_inicio_vigencia", Date.class).toLocalDate())
						.baseCalculoAgricola(t.get("base_calculo_agricola", String.class))
						.idnDC(t.get("indicador_dc", String.class)).build())
				.collect(Collectors.toList());
	}
}
