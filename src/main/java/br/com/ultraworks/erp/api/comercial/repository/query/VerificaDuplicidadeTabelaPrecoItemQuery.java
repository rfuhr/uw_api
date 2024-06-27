package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItem;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeTabelaPrecoItemQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(TabelaPrecoItem tabelaPrecoItem) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeTabelaPrecoItem"), Tuple.class)
				.setParameter("itemId", tabelaPrecoItem.getItem().getId())
				.setParameter("tipoPrecoId", tabelaPrecoItem.getTabelaPreco().getTipoPreco().getId())
				.setParameter("dataInicio", tabelaPrecoItem.getDataInicioVigencia())
				.setParameter("dataFinal", tabelaPrecoItem.getDataFinalVigencia())
				.setParameter("validaId", tabelaPrecoItem.getId() != null ? 0 : 1)
				.setParameter("id", tabelaPrecoItem.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do Item da Tabela de Preço  " + tabelaPrecoItem.getItem().getNome(); 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Item da Tabela de Preço " + tabelaPrecoItem.getItem().getNome() + 
					", pois existe uma Tabela de Preço Vigente que o Item está cadastrado, Tabela de Preço com código: " + resultTuples.iterator().next().get("codigo") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade do Item da Tabela de Preço");
		}
	}
}
