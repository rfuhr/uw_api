package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMarkupItemQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMarkupItem configMarkupItem) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMarkupItem"), Tuple.class)
				.setParameter("itemId", configMarkupItem.getItem().getId())
				.setParameter("dataInicio", configMarkupItem.getDataInicioVigencia())
				.setParameter("dataFinal", configMarkupItem.getDataFinalVigencia())
				.setParameter("validaId", configMarkupItem.getId() != null ? 0 : 1)
				.setParameter("id", configMarkupItem.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Configuração do Mark Up do Item "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração do Mark Up do Item " + configMarkupItem.getItem().getNome() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração do Mark Up do Item");
		}
	}
}
