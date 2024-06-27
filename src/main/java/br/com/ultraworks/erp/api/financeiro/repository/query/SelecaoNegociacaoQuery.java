package br.com.ultraworks.erp.api.financeiro.repository.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoNegociacaoResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecaoNegociacaoQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SelecaoNegociacaoResponse> executeSQL(Long empresaFilialId, Long tipoTituloId, Long departamentoId,
			Long parceiroLocalId, Long caracteristicaMovimentoFinanceiroId, Long carteiraFinanceiraId,
			Long grupoFinanceiroId, Long fatoGeradorId, Long nossoNumero, String documento,
			LocalDate dataMovimentoInicial, LocalDate dataMovimentoFinal, LocalDate dataVencimentoInicial,
			LocalDate dataVencimentoFinal) {
		List<Tuple> resultTuples = em.createNativeQuery(SQLUtils.obterQuery("selecaoNegociacao"), Tuple.class)
				.setParameter("empresaFilialId", empresaFilialId).setParameter("tipoTituloId", tipoTituloId)
				.setParameter("departamentoId", departamentoId).setParameter("parceiroLocalId", parceiroLocalId)
				.setParameter("caracteristicaMovimentoFinanceiroId", caracteristicaMovimentoFinanceiroId)
				.setParameter("carteiraFinanceiraId", carteiraFinanceiraId)
				.setParameter("grupoFinanceiroId", grupoFinanceiroId).setParameter("fatoGeradorId", fatoGeradorId)
				.setParameter("documento", documento).setParameter("nossoNumero", nossoNumero)
				.setParameter("dataMovimentoInicial", dataMovimentoInicial)
				.setParameter("dataMovimentoFinal", dataMovimentoFinal)
				.setParameter("dataVencimentoInicial", dataVencimentoInicial)
				.setParameter("dataVencimentoFinal", dataVencimentoFinal).getResultList();

		return resultTuples.stream()
				.map(t -> SelecaoNegociacaoResponse.builder().saldoParcela(t.get("saldoParcela", BigDecimal.class))
						.carteiraFinanceiraId(t.get("carteiraFinanceiraId", Long.class))
						.siglaCarteira(t.get("siglaCarteira", String.class))
						.tituloId(t.get("tituloId", Long.class)).tipoTituloSigla(t.get("tipoTituloSigla", String.class))
						.documento(t.get("documento", String.class))
						.dataVencimento(t.get("dataVencimento", Date.class).toLocalDate())
						.parceiroLocalId(t.get("parceiroLocalId", Long.class))
						.parceiroLocalCpfCnpj(t.get("parceiroLocalCpfCnpj", String.class))
						.historicoPadraoId(t.get("historicoPadraoId", Long.class))
						.caracteristicaMovimentoId(t.get("caracteristicaMovimentoId", Long.class))
						.caracteristicaMovimentoSigla(t.get("caracteristicaMovimentoSigla", String.class))
						.dataDocumento(t.get("dataDocumento", Date.class) != null
								? t.get("dataDocumento", Date.class).toLocalDate()
								: null)
						.movimentoId(t.get("movimentoId", Long.class)).tipoTituloId(t.get("tipoTituloId", Long.class))
						.numeroParcela(t.get("numeroParcela", Integer.class))
						.parcelaFinanceiraId(t.get("parcelaFinanceiraId", Long.class))
						.movimentoFinanceiroId(t.get("movimentoFinanceiroId", Long.class))
						.sequenciaMovimento(t.get("sequenciaMovimento", Integer.class))
						.nossoNumero(t.get("nossoNumero", Long.class))
						.empresaFilialId(t.get("empresaFilialId", Long.class))
						.empresaFilialSigla(t.get("empresaFilialSigla", String.class))
						.departamentoId(t.get("departamentoId", Long.class))
						.departamentoSigla(t.get("departamentoSigla", String.class))
						.build())
				.collect(Collectors.toList());
	}
}
