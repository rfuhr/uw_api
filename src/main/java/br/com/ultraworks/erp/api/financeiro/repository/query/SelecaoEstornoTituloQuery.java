package br.com.ultraworks.erp.api.financeiro.repository.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoBaixaResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoEstornoResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SelecaoEstornoTituloQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SelecaoEstornoResponse> executeSQL(Long empresaFilialId, Long tipoTituloId, Long departamentoId,
			Long parceiroLocalId, Long caracteristicaMovimentoFinanceiroId, Long carteiraFinanceiraId,
			Long grupoFinanceiroId, Long fatoGeradorId, Long nossoNumero, String documento,
			LocalDate dataMovimentoInicial, LocalDate dataMovimentoFinal, int numeroRecibo) {
		List<Tuple> resultTuples = em.createNativeQuery(SQLUtils.obterQuery("selecaoEstornoTitulo"), Tuple.class)
				.setParameter("empresaFilialId", empresaFilialId).setParameter("tipoTituloId", tipoTituloId)
				.setParameter("departamentoId", departamentoId).setParameter("parceiroLocalId", parceiroLocalId)
				.setParameter("caracteristicaMovimentoFinanceiroId", caracteristicaMovimentoFinanceiroId)
				.setParameter("carteiraFinanceiraId", carteiraFinanceiraId)
				.setParameter("grupoFinanceiroId", grupoFinanceiroId).setParameter("fatoGeradorId", fatoGeradorId)
				.setParameter("documento", documento).setParameter("nossoNumero", nossoNumero)
				.setParameter("dataMovimentoInicial", dataMovimentoInicial)
				.setParameter("dataMovimentoFinal", dataMovimentoFinal).getResultList();

		return resultTuples.stream()
				.map(t -> SelecaoEstornoResponse.builder()
				.tituloId(t.get("tituloId", Long.class))
				.parceiroLocalId(t.get("parceiroLocalId", Long.class))
				.documento(t.get("documento", String.class))
				.nossoNumero(t.get("nossoNumero", Long.class))
				.parcelaFinanceiraId(t.get("parcelaFinanceiraId", Long.class))
				.numeroParcela(t.get("numeroParcela", Integer.class))
				.dataVencimento(t.get("dataVencimento", Date.class).toLocalDate())
				.movimentoFinanceiroId(t.get("movimentoFinanceiroId", Long.class))
				.dataMovimento(t.get("dataMovimento", Date.class).toLocalDate())
				.sequenciaMovimento(t.get("sequenciaMovimento", Integer.class))
				.valorMovimento(t.get("valorMovimento", BigDecimal.class))
				.tipoOperacaoFinanceiraId(t.get("tipoOperacaoFinanceiraId", Long.class))
				.tipoOperacaoFinanceiraNome(t.get("tipoOperacaoFinanceiraNome", String.class))
				.operacaoMovimentoFinanceiroId(t.get("operacaoMovimentoFinanceiroId", Long.class))
				.operacaoAcessoriaFinanceiraId(t.get("operacaoAcessoriaFinanceiraId", Long.class))
				.departamentoSigla(t.get("departamentoSigla", String.class))
				.parceiroLocalCpfCnpj(t.get("parceiroLocalCpfCnpj", String.class))
				.build())
				.collect(Collectors.toList());
	}
}
