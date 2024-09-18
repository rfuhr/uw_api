package br.com.ultraworks.erp.api.financeiro.repository.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoConsultaResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecaoConsultaTituloQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SelecaoConsultaResponse> executeSQL(Long empresaFilialId, Long tipoTituloId, Long departamentoId,
			Long parceiroLocalId, Long caracteristicaMovimentoFinanceiroId, Long carteiraFinanceiraId,
			Long grupoFinanceiroId, Long fatoGeradorId, Long nossoNumero, String documento,
			LocalDate dataMovimentoInicial, LocalDate dataMovimentoFinal) {
		List<Tuple> resultTuples = em.createNativeQuery(SQLUtils.obterQuery("selecaoConsultaTitulo"), Tuple.class)
				.setParameter("empresaFilialId", empresaFilialId).setParameter("tipoTituloId", tipoTituloId)
				.setParameter("departamentoId", departamentoId).setParameter("parceiroLocalId", parceiroLocalId)
				.setParameter("caracteristicaMovimentoFinanceiroId", caracteristicaMovimentoFinanceiroId)
				.setParameter("carteiraFinanceiraId", carteiraFinanceiraId)
				.setParameter("grupoFinanceiroId", grupoFinanceiroId).setParameter("fatoGeradorId", fatoGeradorId)
				.setParameter("documento", documento).setParameter("nossoNumero", nossoNumero)
				.setParameter("dataMovimentoInicial", dataMovimentoInicial)
				.setParameter("dataMovimentoFinal", dataMovimentoFinal).getResultList();

		return resultTuples.stream()
				.map(t -> SelecaoConsultaResponse.builder()
				.tituloId(t.get("tituloId", Long.class))
				.tipoTituloSigla(t.get("tipoTituloSigla", String.class))
				.departamentoSigla(t.get("departamentoSigla", String.class))
				.nossoNumero(t.get("nossoNumero", Long.class))
				.dataDocumento(t.get("dataDocumento", Date.class) != null ? t.get("dataDocumento", Date.class).toLocalDate() : null)
				.parceiroNome(t.get("parceiroNome", String.class))
				.parceiroLocalNome(t.get("parceiroLocalNome", String.class))
				.grupoFinanceiroSigla(t.get("grupoFinanceiroSigla", String.class))
				.fatoGeradorSigla(t.get("fatoGeradorSigla", String.class))
				.caracteristicaMovimentoFinanceiroSigla(t.get("caracteristicaMovimentoFinanceiroSigla", String.class))
				.historicoPadraoSigla(t.get("historicoPadraoSigla", String.class))
				.valorTitulo(t.get("valorTitulo", BigDecimal.class))
				.build())
				.collect(Collectors.toList());
	}
}
