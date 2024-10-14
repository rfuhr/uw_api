package br.com.ultraworks.erp.api.agricola.repository.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaSelecaoResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecionaContratoAgricolaParaFixacaoQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ContratoAgricolaSelecaoResponse> executeSQL(Long parceiroId, Long itemId) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("/agricola/selecionaContratoAgricolaParaFixacao"), Tuple.class)
				.setParameter("parceiroId", parceiroId).setParameter("itemId", itemId)
				.getResultList();

		return resultTuples.stream().map(t -> ContratoAgricolaSelecaoResponse.builder()
				.contratoId(t.get("contratoId", Long.class))
				.parceiroId(t.get("parceiroId", Long.class)).parceiroNome(t.get("parceiroNome", String.class))
				.parceiroLocalId(t.get("parceiroLocalId", Long.class))
				.parceiroLocalNome(t.get("parceiroLocalNome", String.class))
				.itemCodigo(t.get("itemCodigo", Long.class)).departamentoId(t.get("departamentoId", Long.class))
				.departamentoSigla(t.get("departamentoSigla", String.class))
				.departamentoNome(t.get("departamentoNome", String.class))
				.contratoNumero(t.get("contratoNumero", Integer.class))
				.contratoSaldo(t.get("contratoSaldo", BigDecimal.class))
				.contratoDataDocumento(t.get("contratoDataDocumento", Date.class) != null
						? t.get("contratoDataDocumento", Date.class).toLocalDate()
						: null)
				.operacaoInternaId(t.get("operacaoInternaId", Long.class))
				.operacaoInternaSigla(t.get("operacaoInternaSigla", String.class))
				.operacaoInternaNome(t.get("operacaoInternaNome", String.class))
				.contratoObservacao(t.get("contratoObservacao", String.class))
				.grupoOperacaoAgricolaId(t.get("grupoOperacaoAgricolaId", Long.class))
				.grupoOperacaoAgricolaNome(t.get("grupoOperacaoAgricolaNome", String.class))
				.contratoValorUnitarioLiquido(t.get("contratoValorUnitarioLiquido", BigDecimal.class))
				.contratoValorUnitarioBruto(t.get("contratoValorUnitarioBruto", BigDecimal.class))
				.ufId(t.get("ufId", Long.class)).ufSigla(t.get("ufSigla", String.class)).build())
				.collect(Collectors.toList());
	}
}
