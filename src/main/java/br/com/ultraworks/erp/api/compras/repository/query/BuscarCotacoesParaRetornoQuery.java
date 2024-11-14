package br.com.ultraworks.erp.api.compras.repository.query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.compras.vo.CotacaoMercadoriaParaRetornoVO;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BuscarCotacoesParaRetornoQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CotacaoMercadoriaParaRetornoVO> executeSQL() {
		List<CotacaoMercadoriaParaRetornoVO> listaCotacoes = new ArrayList<>();
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("compra/buscarCotacoesParaRetorno"), Tuple.class)
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			for (Tuple tuple : resultTuples) {
				CotacaoMercadoriaParaRetornoVO cotacao = new CotacaoMercadoriaParaRetornoVO();

				cotacao.setCotacaoMercadoriaId(tuple.get("cotacaoMercadoriaId", Long.class));
				cotacao.setCotacaoMercadoriaNumero(tuple.get("cotacaoMercadoriaNumero", Long.class));
				cotacao.setCotacaoMercadoriaData(tuple.get("cotacaoMercadoriaData", Date.class).toLocalDate());
				cotacao.setCotacaoMercadoriaSituacao(tuple.get("cotacaoMercadoriaSituacao", String.class));
				cotacao.setDepartamentoSigla(tuple.get("departamentoSigla", String.class));
				cotacao.setParceiroNomeRazaoSocial(tuple.get("parceiroNomeRazaoSocial", String.class));
				cotacao.setParceiroLocalNome(tuple.get("parceiroLocalNome", String.class));
				cotacao.setCotacaoMercadoriaParceiroId(tuple.get("cotacaoMercadoriaParceiroId", Long.class));
				cotacao.setSituacaoCotacaoMercadoriaName(tuple.get("situacaoCotacaoMercadoriaName", String.class));

				listaCotacoes.add(cotacao);
			}
		}
		return listaCotacoes;

	}

}
