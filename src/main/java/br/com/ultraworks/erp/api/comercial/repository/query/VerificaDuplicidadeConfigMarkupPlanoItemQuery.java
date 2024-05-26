package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMarkupPlanoItemQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMarkupPlanoItem configMarkupPlanoItem) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMarkupPlanoItem"), Tuple.class)
				.setParameter("planoClassificacaoItemId", configMarkupPlanoItem.getPlanoClassificacaoItem().getId())
				.setParameter("dataInicio", configMarkupPlanoItem.getDataInicioVigencia())
				.setParameter("dataFinal", configMarkupPlanoItem.getDataFinalVigencia())
				.setParameter("validaId", configMarkupPlanoItem.getId() != null ? 0 : 1)
				.setParameter("id", configMarkupPlanoItem.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade da Configuração do Mark Up do Plano de Classificação do Item "; 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir a Configuração do Mark Up do Plano de Classificação do Item " + configMarkupPlanoItem.getPlanoClassificacaoItem().getNome() + 
					", pois existe uma Configuração cadastrada com o Identificador: " + resultTuples.iterator().next().get("id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade da Configuração do Mark Up do Plano de Classificação do Item");
		}
	}
}
