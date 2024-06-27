package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMarkupItemIndiceQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMarkupItemIndice configMarkupItemIndice) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMarkupItemIndice"), Tuple.class)
				.setParameter("indiceMarkupId", configMarkupItemIndice.getIndiceMarkup().getId())
				.setParameter("itemId", configMarkupItemIndice.getConfigMarkupItem().getItem().getId())
				.setParameter("dataInicio", configMarkupItemIndice.getDataInicioVigencia())
				.setParameter("dataFinal", configMarkupItemIndice.getDataFinalVigencia())
				.setParameter("validaId", configMarkupItemIndice.getId() != null ? 0 : 1)
				.setParameter("id", configMarkupItemIndice.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do Índice da Configuração de Mark Up do Item  " + configMarkupItemIndice.getIndiceMarkup().getNome(); 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Índice da Configuração de Mark Up do Item " + configMarkupItemIndice.getIndiceMarkup().getNome() + 
					", pois existe uma Configuração cadastrada na Configuração de Mark Up do Item com o Identificador: " + resultTuples.iterator().next().get("config_markup_item_id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade do Índice da Configuração de Mark Up do Item");
		}
	}
}
