package br.com.ultraworks.erp.api.fiscal.repository.query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.MensagemFiscalNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.MensagemFiscalNFeResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaMensagensFiscaisParaNFeQuery {

	EntityManager em;

	@SuppressWarnings({ "unchecked" })
	public MensagemFiscalNFeResponse executeSQL(MensagemFiscalNFeRequest mensagemFiscalNFeRequest) {
		MensagemFiscalNFeResponse response = new MensagemFiscalNFeResponse();

		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("buscaMensagensFiscaisParaNFe"), Tuple.class)
				.setParameter("data", mensagemFiscalNFeRequest.getData())
				.setParameter("parceiroLocalId", mensagemFiscalNFeRequest.getParceiroLocalId())
				.setParameter("operacaoInternaId", mensagemFiscalNFeRequest.getOperacaoInternaId())
				.setParameter("listaItens", converteEmClausulaIN(mensagemFiscalNFeRequest.getListaItens()))
				.setParameter("listaConfiguracoesCOFINS", converteEmClausulaIN(mensagemFiscalNFeRequest.getListaConfiguracaoCOFINS()))
				.setParameter("listaConfiguracoesPIS", converteEmClausulaIN(mensagemFiscalNFeRequest.getListaConfiguracaoPIS()))
				.setParameter("listaConfiguracoesIPI", converteEmClausulaIN(mensagemFiscalNFeRequest.getListaConfiguracaoIPI()))
				.setParameter("listaConfiguracoesICMS", converteEmClausulaIN(mensagemFiscalNFeRequest.getListaConfiguracaoICMS()))
				
				.getResultList();
		
		List<String> listaMensagens = new ArrayList<>();
		if (resultTuples == null || resultTuples.size() == 0) {
			for (Tuple tuple : resultTuples) {
				String mensagem = tuple.get("obs_fiscal").toString();
				listaMensagens.add(mensagem);
			}
			
		}

		response.getListaMensagens().addAll(listaMensagens);
		
		return response;
	}
	
	private String converteEmClausulaIN (List<Long> listaDeLongs) {
        return listaDeLongs.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
	}
}
