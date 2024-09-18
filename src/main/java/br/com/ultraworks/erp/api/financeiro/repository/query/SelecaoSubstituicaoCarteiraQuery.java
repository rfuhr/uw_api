package br.com.ultraworks.erp.api.financeiro.repository.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoSubstituicaoCarteiraResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecaoSubstituicaoCarteiraQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SelecaoSubstituicaoCarteiraResponse> executeSQL(Long empresaFilialId, Long tipoTituloId,
			Long departamentoId, Long parceiroLocalId, Long caracteristicaMovimentoFinanceiroId,
			Long carteiraFinanceiraId, Long grupoFinanceiroId, Long fatoGeradorId, Long nossoNumero, String documento,
			LocalDate dataMovimentoInicial, LocalDate dataMovimentoFinal, LocalDate dataVencimentoInicial,
			LocalDate dataVencimentoFinal, Long carteiraFinanceiraDestinoId) {
		List<Tuple> resultTuples = em.createNativeQuery(SQLUtils.obterQuery("selecaoSubstituicaoCarteira"), Tuple.class)
				.setParameter("empresaFilialId", empresaFilialId).setParameter("tipoTituloId", tipoTituloId)
				.setParameter("departamentoId", departamentoId).setParameter("parceiroLocalId", parceiroLocalId)
				.setParameter("caracteristicaMovimentoFinanceiroId", caracteristicaMovimentoFinanceiroId)
				.setParameter("carteiraFinanceiraId", carteiraFinanceiraId)
				.setParameter("grupoFinanceiroId", grupoFinanceiroId).setParameter("fatoGeradorId", fatoGeradorId)
				.setParameter("documento", documento).setParameter("nossoNumero", nossoNumero)
				.setParameter("dataMovimentoInicial", dataMovimentoInicial)
				.setParameter("dataMovimentoFinal", dataMovimentoFinal)
				.setParameter("dataVencimentoInicial", dataVencimentoInicial)
				.setParameter("dataVencimentoFinal", dataVencimentoFinal)
				.setParameter("carteiraFinanceiraDestinoId", carteiraFinanceiraDestinoId).getResultList();

		return resultTuples.stream().map(t -> SelecaoSubstituicaoCarteiraResponse.builder()
				.tituloId(t.get("tituloId", Long.class)).parcelaFinanceiraId(t.get("parcelaFinanceiraId", Long.class))
				.movimentoFinanceiroId(t.get("movimentoFinanceiroId", Long.class))
				.nossoNumero(t.get("nossoNumero", Long.class)).departamentoId(t.get("departamentoId", Long.class))
				.departamentoSigla(t.get("departamentoSigla", String.class))
				.departamentoNome(t.get("departamentoNome", String.class))
				.parceiroNome(t.get("parceiroNome", String.class))
				.parceiroLocalCpfCnpj(t.get("parceiroLocalCpfCnpj", String.class))
				.parceiroLocalNome(t.get("parceiroLocalNome", String.class))
				.carteiraFinanceiraSigla(t.get("carteiraFinanceiraSigla", String.class))
				.carteiraFinanceiraNome(t.get("carteiraFinanceiraNome", String.class))
				.documento(t.get("documento", String.class)).numeroParcela(t.get("numeroParcela", Integer.class))
				.dataVencimento(
						t.get("dataVencimento", Date.class) != null ? t.get("dataVencimento", Date.class).toLocalDate()
								: null)
				.saldoParcela(t.get("saldoParcela", BigDecimal.class))
				.caracteristicaMovimentoId(t.get("caracteristicaMovimentoId", Long.class))
				.caracteristicaMovimentoSigla(t.get("caracteristicaMovimentoSigla", String.class))
				.caracteristicaMovimentoNome(t.get("caracteristicaMovimentoNome", String.class))
				.empresaFilialSigla(t.get("empresaFilialSigla", String.class))
				.empresaFilialNome(t.get("empresaFilialNome", String.class))
				.build()).collect(Collectors.toList());
	}
}
