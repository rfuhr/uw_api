package br.com.ultraworks.erp.api.comercial.repository.query;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndice;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import br.com.ultraworks.erp.core.util.SQLUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificaDuplicidadeConfigMarkupPlanoItemIndiceQuery {

	EntityManager em;

	@SuppressWarnings("unchecked")
	public void executeSQL(ConfigMarkupPlanoItemIndice configMarkupPlanoItemIndice) {
		List<Tuple> resultTuples = em
				.createNativeQuery(SQLUtils.obterQuery("verificaDuplicidadeConfigMarkupPlanoItemIndice"), Tuple.class)
				.setParameter("indiceMarkupId", configMarkupPlanoItemIndice.getIndiceMarkup().getId())
				.setParameter("planoClassificacaoItemId", configMarkupPlanoItemIndice.getConfigMarkupPlanoItem().getPlanoClassificacaoItem().getId())
				.setParameter("dataInicio", configMarkupPlanoItemIndice.getDataInicioVigencia())
				.setParameter("dataFinal", configMarkupPlanoItemIndice.getDataFinalVigencia())
				.setParameter("validaId", configMarkupPlanoItemIndice.getId() != null ? 0 : 1)
				.setParameter("id", configMarkupPlanoItemIndice.getId())
				.getResultList();

		if (resultTuples != null && resultTuples.size() > 0) {
			ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
			String label = "Validação de Duplicidade do Índice da Configuração de Mark Up do Plano de Classificação do Item  " + configMarkupPlanoItemIndice.getIndiceMarkup().getNome(); 
			validationErrorResponse.getErrors().add(new ValidationError(label,
					"Não é possível incluir o Índice da Configuração de Mark Up do Plano de Classificação do Item " + configMarkupPlanoItemIndice.getIndiceMarkup().getNome() + 
					", pois existe uma Configuração cadastrada na Configuração de Mark Up do Plano de Classificação do Item com o Identificador: " + resultTuples.iterator().next().get("config_markup_plano_item_id") + "."));
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de Duplicidade do Índice da Configuração de Mark Up do Plano de Classificação do Item");
		}
	}
}
