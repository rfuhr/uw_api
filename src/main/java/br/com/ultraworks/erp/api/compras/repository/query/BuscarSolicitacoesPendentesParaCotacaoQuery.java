package br.com.ultraworks.erp.api.compras.repository.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.vo.SolicitacaoMercadoriaItemParaCotacaoVO;
import br.com.ultraworks.erp.api.compras.vo.SolicitacaoMercadoriaParaCotacaoVO;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BuscarSolicitacoesPendentesParaCotacaoQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SolicitacaoMercadoriaParaCotacaoVO> executeSQL(Long departamentoSolicitadoId) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("compra/buscarSolicitacoesPendentesParaCotacao"), Tuple.class)
				.setParameter("departamentoSolicitadoId", departamentoSolicitadoId).getResultList();

		Map<Long, SolicitacaoMercadoriaParaCotacaoVO> solicitacaoMap = resultTuples.stream()
				.collect(Collectors.toMap(t -> t.get(0, Long.class), t -> {
					return SolicitacaoMercadoriaParaCotacaoVO.builder().id(t.get("id", Long.class))
							.numero(t.get("numero", Long.class))
							.dataSolicitacao(t.get("dataSolicitacao", Date.class).toLocalDate())
							.departamentoSolicitadoId(t.get("departamentoSolicitadoId", Long.class))
							.departamentoSolicitadoSigla(t.get("departamentoSolicitadoSigla", String.class))
							.departamentoSolicitanteId(t.get("departamentoSolicitanteId", Long.class))
							.departamentoSolicitanteSigla(t.get("departamentoSolicitanteSigla", String.class)).build();
				}, (existing, replacement) -> existing));

		resultTuples.stream().forEach(t -> {
			Long solicitacaoId = t.get(0, Long.class);
			SolicitacaoMercadoriaParaCotacaoVO solicitacao = solicitacaoMap.get(solicitacaoId);
			if (solicitacao.getItens() == null) {
				solicitacao.setItens(new ArrayList<>());
			}
			if (solicitacao != null) {
				SolicitacaoMercadoriaItemParaCotacaoVO item = SolicitacaoMercadoriaItemParaCotacaoVO.builder()
						.id(t.get("solicitacaoMercadoriaItemId", Long.class))
						.solicitacaoMercadoriaId(t.get("id", Long.class)).itemId(t.get("itemId", Long.class))
						.itemNome(t.get("itemNome", String.class))
						.itemSimplificadoId(t.get("itemSimplificadoId", Long.class))
						.itemSimplificadoNome(t.get("itemSimplificadoNome", String.class))
						.departamentoEntregaId(t.get("departamentoEntregaId", Long.class))
						.departamentoEntregaSigla(t.get("departamentoEntregaSigla", String.class))
						.quantidadeSolicitada(t.get("quantidadeSolicitada", BigDecimal.class))
						.observacao(t.get("observacaoItem", String.class))
						.usuarioSolicitacaoNome(t.get("usuarioSolicitacaoNome", String.class))
						.previsaoDiasUtilizacao(t.get("previsaoDiasUtilizacao", Integer.class))
						.urgenciaSolicitacaoMercadoria(t.get("urgenciaSolicitacaoMercadoria", String.class)).build();
				solicitacao.getItens().add(item);
			}
		});

		return new ArrayList<>(solicitacaoMap.values());
	}

}
